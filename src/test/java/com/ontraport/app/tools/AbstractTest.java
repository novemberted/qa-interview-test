package com.ontraport.app.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.ontraport.app.pages.OntraportLogin;


/**
 * Abstract superclass for all tests. Holds code related to test startup and shutdown,
 * as well as (eventually) logging in, recovering additional test logs, and taking screenshots
 * of failed tests.
 *
 * @author jason
 * @since 5/24/16
 */
public abstract class AbstractTest extends AbstractBase
{
    private int errorCount = 0;
    LoginInfo currentAccount;

    static AtomicInteger threads = new AtomicInteger(0);
    static AtomicInteger testNumber = new AtomicInteger(0);
    int thisTest;

    protected final String UNIQUE = Long.toString(System.currentTimeMillis());

    /**
     * Set up the webdriver instance
     */
    protected void setupDriver () throws IOException
    {
        String urlName = "http://172.29.4.8:4444/wd/hub";
        URL url = new URL(urlName);

        String headerString = System.getProperty("header");
        if ( headerString == null )
        {
            headerString = "OntraportStaging";
        }
//        System.out.println("Using header: " + headerString);

        FirefoxProfile profile = new FirefoxProfile();

        File modifyHeadersExtension = new File("modify_headers-0.7.1.1-fx.xpi");
        profile.addExtension(modifyHeadersExtension);

        //set up the modify headers plugin
        profile.setPreference("modifyheaders.config.active", true);
        profile.setPreference("modifyheaders.config.alwaysOn", true);
        //it looked like this option was necessary, but it isn't. Leaving it here because its not documented.
        //profile.setPreference("modifyheaders.config.openNewTab", true);
        profile.setPreference("modifyheaders.start", true);

        //tell it to modify the X-op-env property
        profile.setPreference("modifyheaders.headers.count", 1);
        profile.setPreference("modifyheaders.headers.action0", "Add");
        profile.setPreference("modifyheaders.headers.name0", "X-op-env");
        profile.setPreference("modifyheaders.headers.value0", headerString);
        profile.setPreference("modifyheaders.headers.enabled0", true);

        LoggingPreferences lop = new LoggingPreferences();
        lop.enable(LogType.BROWSER, Level.ALL);

        DesiredCapabilities cap = DesiredCapabilities.firefox();
        cap.setCapability(FirefoxDriver.PROFILE, profile);
        cap.setCapability(CapabilityType.LOGGING_PREFS, lop);

        String rfg = System.getProperty("run.from.gradle", "false");
        if ( rfg.equals("true") )
        {
            driver = new RemoteWebDriver(url, cap);
        }
        else
        {
//            driver = new FirefoxDriver(cap);
            driver = new RemoteWebDriver(url, cap);
        }

        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1000));

        wait = new WebDriverWait(driver, DEFAULT_WAIT);
    }

    public void loginAs () throws IOException
    {
        //initialized to a fallback
        String accountNameString = "any";

        //override if command line set
        String accountOverride = System.getProperty("account");
        if ( accountOverride != null && accountOverride.trim().isEmpty() == false )
        {
//            System.out.println("Global account settings and defaults overriden!");
            accountNameString = accountOverride;
        }
//        System.out.println("Asking for account: " + accountNameString);

        currentAccount = LoginFactory.getAccount(accountNameString);

        driver.get(AbstractPage.getUrl() + "/#!/contact/listAll");
        OntraportLogin ontraportLogin = new OntraportLogin(driver);
        ontraportLogin.loginAs(currentAccount.getUsername(), currentAccount.getPassword());
        try
        {
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
        }

        driver.get(AbstractPage.getUrl() + "/#!/contact/listAll");
    }

    /**
     * Set up the WebDriver instance. Might want login code to be called eventually too.
     * Really anything that needs to be done before each and every test can be done here.
     */
    @BeforeClass
    public void setup () throws IOException
    {
        int myThreads;
        thisTest = testNumber.getAndIncrement();
        myThreads = threads.incrementAndGet();
        System.out.println(String.format("> Starting test %d. Now %d threads.", thisTest, myThreads));
        setupDriver();
        loginAs();
    }

    /**
     * Called after every test method. Here's a good place to check the status of the test and do
     * any custom reporting (like screenshots) that's beyond TestNG's builtin reporting
     *
     * @param result {@link ITestResult} object that is passed in automatically by TestNG
     */
    @AfterMethod
    public void afterMethod (ITestResult result)
    {
        if ( result.isSuccess() == false )
        {
            System.out.println(driver.getCurrentUrl());
            takeScreenshot(result.getName().trim());
        }
    }

    /**
     * Called after every test method in the class has been executed.
     */
    @AfterClass
    public void cleanup ()
    {
        int myThreads;
        driver.quit();
        myThreads = threads.getAndDecrement();
        System.out.println(String.format("Finished < test %d. Were %d threads.", thisTest, myThreads));
    }

    /**
     * Takes a screenshot of the browser. This supports taking multiple screenshots without the files
     * overwriting each other
     *
     * @param name the base name of the screenshot
     */
    public void takeScreenshot (String name)
    {
        try
        {
            FileOutputStream out = new FileOutputStream(String.format("screenshots/failure-%s-%d.png", name, errorCount++));
            out.write(driver.getScreenshotAs(OutputType.BYTES));
            out.close();
        }
        catch (Exception e)
        {
            //catch exceptions here so they dont add noise to the test reporting
            System.err.println(String.format("Unable to take screenshot number %d for %s", errorCount, name));
        }
    }
}