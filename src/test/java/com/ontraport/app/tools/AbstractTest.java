package com.ontraport.app.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;


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
    private static int errorCount = 0;
    private long UNIQUE = System.nanoTime();

    private String headerString = "OntraportStaging";

    /**
     * Set up the webdriver instance
     */
    protected void setupDriver () throws IOException
    {
        FirefoxProfile profile = new FirefoxProfile();

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);

        driver = new FirefoxDriver(capabilities);

        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1000));

        //FIXME make me a method please
        wait = new WebDriverWait(driver, AbstractBase.DEFAULT_WAIT);
    }

    /**
     * Set up the WebDriver instance. Might want login code to be called eventually too.
     * Really anything that needs to be done before each and every test can be done here.
     */
    @BeforeClass
    public void setup () throws IOException
    {
        setupDriver();
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
            takeScreenshot(result.getName().trim());
        }
    }

    /**
     * Called after every test method in the class has been executed.
     */
    @AfterClass
    public void cleanup ()
    {
        driver.quit();
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