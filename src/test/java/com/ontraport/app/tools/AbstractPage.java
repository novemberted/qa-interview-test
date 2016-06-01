package com.ontraport.app.tools;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/24/16
 */
public abstract class AbstractPage extends AbstractBase
{
    protected static String url = "https://app.ontraport.com/";

    public AbstractPage ()
    {
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, com.ontraport.app.tools.AbstractBase.DEFAULT_WAIT);
        PageFactory.initElements(finder, this);
    }

    /**
     * Gets the AbstractPage's URL.
     *
     * @return String - value of the url.
     */
    public static String getUrl ()
    {
        return AbstractPage.url;
    }
}