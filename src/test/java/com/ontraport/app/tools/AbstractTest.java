package com.ontraport.app.tools;

import java.io.FileOutputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;


/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/24/16
 */
public abstract class AbstractTest extends AbstractBase
{
    private static int errorCount = 0;

    /**
     * Set up the webdriver instance
     */
    protected void setupDriver ()
    {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, DEFAULT_WAIT);
    }

    /**
     * Constructor sets up the driver. Might want login code to be called eventually too.
     * Really anything that needs to be done before each and every test can be done here.
     */
    @BeforeClass
    public void setup ()
    {
        setupDriver();
    }

    @AfterMethod
    public void afterMethod (ITestResult result)
    {
        if ( result.isSuccess() == false )
        {
            takeScreenshot(result.getName().trim());
        }
    }

    @AfterClass
    public void cleanup ()
    {
        driver.quit();
    }

    public void takeScreenshot (String name)
    {
        try
        {
            FileOutputStream out = new FileOutputStream("screenshots/failure-" + name + "-" + errorCount++ + ".png");
            out.write(driver.getScreenshotAs(OutputType.BYTES));
            out.close();
        }
        catch (Exception e)
        {
            //catch exceptions here so they dont add noise to the test reporting
        }
    }
}