package com.ontraport.app.tools;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/24/16
 */
public abstract class AbstractBase
{
    protected RemoteWebDriver driver;
    protected WebDriverWait wait;
    static final int DEFAULT_WAIT = 15;


    protected WebElement el (By locator, int timeout)
    {
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement el (By locator)
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected List<WebElement> els (By locator, int timeout)
    {
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected List<WebElement> els (By locator)
    {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
}