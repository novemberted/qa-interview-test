package com.ontraport.app.tools;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Abstract superclass for parts that can be embedded in multiple page classes,
 * or can even go in AbstractPage if they are common to all pages.
 *
 * @author jason
 * @since 5/24/16
 */
public abstract class AbstractPart extends AbstractBase
{
    public AbstractPart(RemoteWebDriver d)
    {
        driver = d;
        wait = new WebDriverWait(driver, DEFAULT_WAIT);
    }
}
