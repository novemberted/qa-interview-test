package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import tools.AbstractPage;

/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/24/16
 */
public class OntrapagesLogin extends AbstractPage
{
    By usernameInput = By.cssSelector("#username");

    By passwordInput = By.cssSelector("#password");

    By loginButton = By.cssSelector("#login_button");

    public OntrapagesLogin (RemoteWebDriver d, WebDriverWait w)
    {
        super(d, w);
    }

    public LandingPage_ListAll loginAs (String username, String password)
    {
        el(usernameInput).sendKeys(username);
        el(passwordInput).sendKeys(password);
        el(loginButton).click();
        return new LandingPage_ListAll(driver, wait);
    }
}
