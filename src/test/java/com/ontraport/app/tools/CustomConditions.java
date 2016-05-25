package com.ontraport.app.tools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.google.common.base.Function;

/**
 * Holds our custom expected conditions for use with
 * {@link org.openqa.selenium.support.ui.WebDriverWait#until(Function)}
 * <p/>
 * Use static objects that extend {@link org.openqa.selenium.support.ui.ExpectedCondition}
 * <p/>
 * See the selenium source code for {@link org.openqa.selenium.support.ui.ExpectedConditions} for an example of
 * what this class should look like.
 *
 * @author jason
 * @since 5/24/16
 */
public class CustomConditions
{
    public static ExpectedCondition<Boolean> latchIsClear = new ExpectedCondition<Boolean>()
    {
        @Override
        public Boolean apply (WebDriver driverObject)
        {
            return (Boolean) ((JavascriptExecutor) driverObject).executeScript("return ontraport.activeRequests === 0");
        }
    };
}
