package com.ontraport.app.tools;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/24/16
 */
public abstract class AbstractTest extends AbstractBase
{
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
    public AbstractTest ()
    {
        setupDriver();
    }
}