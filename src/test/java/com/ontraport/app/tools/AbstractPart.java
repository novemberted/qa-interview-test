package com.ontraport.app.tools;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Abstract superclass for parts that can be embedded in multiple page classes,
 * or can even go in AbstractPage if they are common to all pages.
 *
 * @author jason
 * @since 5/24/16
 */
public abstract class AbstractPart extends AbstractBase
{
    public AbstractPart ()
    {
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, com.ontraport.app.tools.AbstractBase.DEFAULT_WAIT);
        PageFactory.initElements(finder, this);
    }
}
