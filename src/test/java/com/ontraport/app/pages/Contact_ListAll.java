package com.ontraport.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ontraport.app.tools.AbstractPage;
import com.ontraport.app.tools.CustomConditions;

/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/24/16
 */
public class Contact_ListAll extends AbstractPage
{
    By newContactButton = By.cssSelector("#ontraport_panel_action_new");

    public Contact_ListAll (RemoteWebDriver d)
    {
        super(d);
    }

    public Contact_Edit clickNewContact ()
    {
        wait.until(CustomConditions.latchIsClear);
    }
}
