package com.ontraport.app.parts;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ontraport.app.tools.AbstractPart;

/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/26/16
 */
public class PaneHeader extends AbstractPart
{
    By backLeft = By.cssSelector(".ussr-chrome-panel-pane-header-back");

    public PaneHeader (RemoteWebDriver d)
    {
        super(d);
    }

    public void clickBackLeft()
    {
        waitForAjax();
        el(backLeft).click();
    }
}
