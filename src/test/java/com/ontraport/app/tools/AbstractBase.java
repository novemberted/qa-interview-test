package com.ontraport.app.tools;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Abstract superclass to everything. Holds common code for basic Selenium stuff.
 *
 * @author jason
 * @since 5/24/16
 */
public abstract class AbstractBase
{
    protected static RemoteWebDriver driver;
    protected static WebDriverWait wait;
    static final int DEFAULT_WAIT = 15;

    protected static LoginInfo currentAccount;

    @FindBy(css = ".ussr-form-state-active ul.ussr-component-drilldownselect-ul")
    private WebElement dropDownCollection;

    /**
     * selects the given list option (Dropdown or List Selection) in the currently open dropdown
     *
     * @param option      display text of the option
     * @param strictMatch if true this will only work when option matches exactly, if false it will click the first thing that contains option
     */
    private void selectDropDownOption (String option, boolean strictMatch)
    {
        AbstractPart.waitForAjax(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(dropDownCollection));

        //get a list of all items in the dropdown
        List<WebElement> dropDownItems = dropDownCollection.findElements(By.xpath(".//li/div | .//li/span"));
        WebElement item = null;
        //look through all items in the dropdown for one that matches what we want
        for (WebElement i : dropDownItems)
        {
            if ( strictMatch == true )
            {
                if ( i.getAttribute("textContent").trim().equalsIgnoreCase(option) )
                {
                    item = i;
                    break;
                }
            }
            else
            {
                //non strict matching (there's no containsIgnoreCase() unfortunately)
                if ( i.getAttribute("textContent").trim().toLowerCase().contains(option.toLowerCase()) )
                {
                    item = i;
                    break;
                }
            }
        }
        if ( item == null )
        {
            throw new NoSuchElementException("An item with the text '" + option + "' was not found in the dropdown.");
        }

        List<WebElement> allDropDowns = driver.findElements(By.cssSelector(".ussr-form-state-active.ussr-component-drilldownselect-menu"));
        WebElement currentDropDown = allDropDowns.get(0);
        for (WebElement e : allDropDowns)
        {
            if (e.isDisplayed())
            {
                currentDropDown = e;
            }
        }
        WebElement scrollTrack = currentDropDown.findElement(By.cssSelector(".jb-ace-scroll-track"));
        WebElement scrollBox = currentDropDown.findElement(By.cssSelector(".jb-ace-scroll-scrollbar"));

        int itemPosition = item.getLocation().getY();

        int dropDownHeight = dropDownCollection.getSize().getHeight();

        int scrollBarTop = scrollTrack.getLocation().getY();
        int scrollBarHeight = scrollTrack.getSize().getHeight();

        int scrollBoxHeight = scrollBox.getSize().getHeight();

        int dropDownRange = dropDownHeight - scrollBarHeight;
        int scrollBoxRange = scrollBarHeight - scrollBoxHeight;

        //if the item location is between scrollBarTop and
        if ( item.isDisplayed() )
        {
            //do nothing, the item is already visible in the dropdown
        }
        else
        {
            //our goal is for itemPosition == scrollBarTop || scrollBoxTop = scrollBarTop + scrollBoxRange
            //how far is itemPosition from scrollBarTop?
            int itemDistance = itemPosition - scrollBarTop;
            //what is the ratio of that to dropDownRange?
            double itemDistanceRatio = (double) itemDistance / (double) dropDownRange;
            //how much of scrollBoxRange is that ratio?
            int scrollBoxDistance = (int) Math.ceil(itemDistanceRatio * scrollBoxRange);
            //move the scroll box
            wait.until(ExpectedConditions.visibilityOf(scrollBox));
            Actions action = new Actions(driver);
            action.clickAndHold(scrollBox);
            //scroll to option using the calculated offset
            action.moveByOffset(0, scrollBoxDistance);
            action.release();
            action.build().perform();
        }

        //Verify the option is currently clickable and click it.
        clickElement(item);
    }

    /**
     * select drop down option with strict matching
     *
     * @param option the option to select
     */
    public void selectDropDownOption (String option)
    {
        selectDropDownOption(option, true);
    }

    /**
     * selects the first option (Dropdown or List Selection) containing the specified value in the
     * currently open menu
     *
     * @param optionContains the full or partial string of option to select
     */
    public void selectDropDownOptionContaining (String optionContains)
    {
        selectDropDownOption(optionContains, false);
    }


    /**
     * Waits for ajax, waits for visibility, optionally scrolls, then clicks on a WebElement.
     *
     * @param e The WebElement to click.
     */
    protected void clickElement (WebElement e)
    {
        AbstractPart.waitForAjax(driver, AbstractPart.DEFAULT_WAIT);
        scrollIfNeeded(e);
        wait.until(ExpectedConditions.visibilityOf(e));
        wait.until(ExpectedConditions.elementToBeClickable(e));
        e.click();
    }

    /**
     * Determines if a web element is currently visible/displayed, and if it's not it attempts to scroll to it.
     *
     * @param webElement The element to work with
     */
    protected void scrollIfNeeded (WebElement webElement)
    {
        if ( webElement.isDisplayed() == false )
        {
            driver.executeScript("arguments[0].scrollIntoView();", webElement);
        }
    }
}