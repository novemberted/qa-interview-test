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
    public AbstractPage ()
    {
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, AbstractBase.DEFAULT_WAIT);
        PageFactory.initElements(finder, this);
    }
}
