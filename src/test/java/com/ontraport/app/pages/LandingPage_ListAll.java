package com.ontraport.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ontraport.app.tools.AbstractPage;

/**
 * @author jason
 * @since 5/24/16
 */
public class LandingPage_ListAll extends AbstractPage
{
    String pageUrl = "#!/landing_page/listAll";

    By createNewOntrapageButton = By.cssSelector(".js-trigger-action__create");

    public LandingPage_ListAll (RemoteWebDriver d)
    {
        super(d);
    }

    /**
     * Clicks the "Create New Ontrapage" button
     */
    public void clickCreateNewOntrapage ()
    {
        el(createNewOntrapageButton).click();
    }

    /**
     * Verifies that the browser is currently at landing_page/listAll
     *
     * @return boolean
     */
    public boolean verifyPage ()
    {
        return wait.until(ExpectedConditions.urlContains(pageUrl));
    }
}
