package com.ontraport.app.tools;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/24/16
 */
public abstract class AbstractPage extends AbstractBase
{
    static String baseUrl = "https://app.ontraport.com";
    static String latch = "/?track_requests=1";

    public static String getUrl()
    {
        return baseUrl + latch;
    }

    public AbstractPage (RemoteWebDriver d)
    {
        driver = d;
        wait = new WebDriverWait(driver, DEFAULT_WAIT);
    }
}
