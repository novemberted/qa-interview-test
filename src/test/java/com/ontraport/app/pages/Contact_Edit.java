package com.ontraport.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ontraport.app.parts.PaneHeader;
import com.ontraport.app.tools.AbstractPage;

/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/25/16
 */
public class Contact_Edit extends AbstractPage
{
    PaneHeader paneHeader;

    public Contact_Edit (RemoteWebDriver d)
    {
        super(d);
        paneHeader = new PaneHeader(driver);
    }

    public Contact_ListAll clickBack ()
    {
        paneHeader.clickBackLeft();
        return new Contact_ListAll(driver);
    }

    /**
     * Used to enter a specified value into a specified field.
     * Note this method DOES NOT click a Save button. This method
     * should be used for any field without a Save button. There are
     * no Saves with Custom Object record creations when the Custom
     * Object has a required field associated with it...which they
     * usually do.
     *
     * @param fieldName  name of the field to enter value into.
     * @param fieldValue value to enter into the field.
     */
    public void enterFieldValue (String fieldName, String fieldValue)
    {
        By activeField = By.cssSelector(".ussr-form-input.ussr-form-state-active");

        waitForAjax(20);
        WebElement fieldContainer = el(By.xpath("//label[normalize-space(.)='" + fieldName + "']//ancestor::div[contains(concat(' ', normalize-space(@class), ' '), ' ussr-form-cell ')]"));
        fieldContainer.click();
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(activeField));
        input.clear();
        input.sendKeys(fieldValue);
        input.sendKeys(Keys.ENTER);
        fieldContainer.click();
    }
}
