package com.ontraport.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ontraport.app.tools.AbstractPage;

/**
 * @author jason
 * @since 5/24/16
 */
public class OntrapagesLogin extends AbstractPage
{
    @FindBy(css = "#username")
    WebElement usernameInput;

    @FindBy(css = "#password")
    WebElement passwordInput;

    @FindBy(css = "#login_button")
    WebElement loginButton;

    /**
     * Login using the specified username and password
     *
     * @param username the username to use
     * @param password password for the account
     * @return LandingPage_ListAll
     */
    public LandingPage_ListAll loginAs (String username, String password)
    {
        driver.get("https://app.ontrapages.com");
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new LandingPage_ListAll();
    }
}
