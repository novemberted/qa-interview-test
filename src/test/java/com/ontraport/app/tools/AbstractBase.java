package com.ontraport.app.tools;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Abstract superclass to everything. Holds common code for basic Selenium stuff.
 *
 * @author jason
 * @since 5/24/16
 */
public abstract class AbstractBase
{
    protected static RemoteWebDriver driver;
    protected static WebDriverWait wait;
    static final int DEFAULT_WAIT = 15;
}