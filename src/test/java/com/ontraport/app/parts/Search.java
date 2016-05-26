package com.ontraport.app.parts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ontraport.app.tools.AbstractPart;

/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/26/16
 */
public class Search extends AbstractPart
{
    By searchBox = By.cssSelector("input[type='search']");

    By cancelButton = By.cssSelector(".chrome-action-bar-search .ussr-form-input-type-clear");

    public Search (RemoteWebDriver d)
    {
        super(d);
    }

    public void find (String what)
    {
        waitForAjax();
        el(searchBox).sendKeys(what + Keys.ENTER);
    }

    public void clickCancel ()
    {
        waitForAjax();
        el(cancelButton).click();
    }
}
