package com.ontraport.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ontraport.app.tools.AbstractPage;

/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/24/16
 */
public class OntraportLogin extends AbstractPage
{
    By usernameInput = By.cssSelector("#username");

    By passwordInput = By.cssSelector("#password");

    By loginButton = By.cssSelector("#login_button");

    public OntraportLogin (RemoteWebDriver d)
    {
        super(d);
    }

    public LandingPage_ListAll loginAs (String username, String password)
    {
        driver.get("https://app.ontrapages.com");
        el(usernameInput).sendKeys(username);
        el(passwordInput).sendKeys(password);
        el(loginButton).click();
        return new LandingPage_ListAll(driver);
    }
}
