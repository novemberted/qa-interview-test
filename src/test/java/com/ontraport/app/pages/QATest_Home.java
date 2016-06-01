package com.ontraport.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ontraport.app.tools.AbstractPage;

public class QATest_Home extends AbstractPage {
	@FindBy(xpath = "//a[contains(.,'PRICING')]")
    private WebElement pricingLink;

    @FindBy(css = "input[class*='login-field login-first']")
    public WebElement firstNameInput;

    @FindBy(css = "input[class*='login-field login-last']")
    public WebElement lastNameInput;

    @FindBy(css = "input[class*='login-field login-email']")
    public WebElement emailInput;

    @FindBy(css = "input[class*='login-field login-password']")
    public WebElement passwordInput;

    @FindBy(css = "input[class*='login-field login-confpassword']")
    public WebElement confPasswordInput;

    @FindBy(css = "input[class*='login-field login-submit']")
    public WebElement submitButton;


    public void clickPricing ()
    {
        wait.until(ExpectedConditions.visibilityOf(pricingLink));
        pricingLink.click();
    }
    
    public void enterFirstName(String firstName)
    {
    	enterText(firstNameInput, firstName);
    }
    
    public void enterLastName(String lastName)
    {
    	enterText(lastNameInput, lastName);
    }
    
    public void enterEmail(String email)
    {
    	enterText(emailInput, email);
    }
    
    public void enterPassword(String password)
    {
    	enterText(passwordInput, password);
    }
    
    public void enterConfPassword(String confPassword)
    {
    	enterText(confPasswordInput, confPassword);
    }
    
    public void clickSubmit()
    {
    	clickElement(submitButton);
    }

}
