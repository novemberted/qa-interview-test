package com.ontraport.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ontraport.app.tools.AbstractPage;

/**
 * @author jason
 * @since 5/24/16
 */
public class QATest_Home extends AbstractPage
{
    @FindBy(xpath = "//a[contains(.,'PRICING')]")
    private WebElement pricingLink;

    public void clickPricing ()
    {
        wait.until(ExpectedConditions.visibilityOf(pricingLink));
        pricingLink.click();
    }
}
