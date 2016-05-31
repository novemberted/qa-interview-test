package com.ontraport.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ontraport.app.tools.AbstractPage;

/**
 * @author jason
 * @since 5/24/16
 */
public class LandingPage_ListAll extends AbstractPage
{
    String pageUrl = "#!/landing_page/listAll";

    @FindBy(css = ".js-trigger-action__create")
    private WebElement createNewOntrapageButton;

    /**
     * Clicks the "Create New Ontrapage" button
     */
    public void clickCreateNewOntrapage ()
    {
        clickElement(createNewOntrapageButton);
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
