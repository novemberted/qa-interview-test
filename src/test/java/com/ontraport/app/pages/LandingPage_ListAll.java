package com.ontraport.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ontraport.app.tools.AbstractPage;

/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/24/16
 */
public class LandingPage_ListAll extends AbstractPage
{
    String pageUrl = "#!/landing_page/listAll";

    By createNewOntrapageButton = By.cssSelector(".js-trigger-action__create");

    public LandingPage_ListAll (RemoteWebDriver d, WebDriverWait w)
    {
        super(d, w);
    }

    public void clickCreateNewOntrapage ()
    {
        el(createNewOntrapageButton).click();
    }

    public boolean verifyPage()
    {
        return wait.until(ExpectedConditions.urlContains(pageUrl));
    }
}
