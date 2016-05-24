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
public class LandingPage_ListAll extends AbstractPage
{
    By createNewOntrapageButton = By.cssSelector(".js-trigger-action__create");

    public LandingPage_ListAll (RemoteWebDriver d, WebDriverWait w)
    {
        super(d, w);
    }

    public void clickCreateNewOntrapage ()
    {
        el(createNewOntrapageButton).click();
    }
}
