package com.ontraport.app.tools;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Abstract superclass for parts that can be embedded in multiple page classes,
 * or can even go in AbstractPage if they are common to all pages.
 *
 * @author jason
 * @since 5/24/16
 */
public abstract class AbstractPart extends AbstractBase
{
    public AbstractPart ()
    {
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, AbstractBase.DEFAULT_WAIT);
        PageFactory.initElements(finder, this);
    }

    public static boolean waitForAjax (WebDriver driver, int timeOutInSeconds)
    {
        long start = System.currentTimeMillis();
        boolean jQcondition = false;
        try
        {
            new WebDriverWait(driver, timeOutInSeconds){}.until(CustomConditions.latchIsClear);

            jQcondition = (Boolean) ((JavascriptExecutor) driver)
                                    .executeScript("return window.ontraport != undefined "
                                                   + "&& ontraport.activeRequests != undefined "
                                                   + "&& ontraport.activeRequests === 0");
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ussr-state-loading")));
            driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
            return jQcondition;
        }
        catch (Exception e)
        {
            System.out.println("Latch Time Out At: " + driver.getCurrentUrl());
            printOurStackFrames(e);
            //cast time to a float, so we can divide to get seconds and not lose precision
            long finish = System.currentTimeMillis();
            double time = (double) finish - (double) start;
            System.out.println("after " + (time / 1000) + " seconds. (expected " + timeOutInSeconds + "s)");
            Object latchReturned = "no latch returned!";
            try
            {
                latchReturned = ((JavascriptExecutor) driver).executeScript("return ontraport.activeRequests");
                int activeRequests = Integer.parseInt(latchReturned.toString());
                System.out.println("Waiting for " + activeRequests + " more active requests");

                ((JavascriptExecutor) driver).executeScript("ontraport.activeRequests = 0");
            }
            catch (WebDriverException w)
            {
                System.out.println("Ontraport is not defined.");
                printOurStackFrames(w);
            }
            catch (NumberFormatException n)
            {
                System.out.println("Latch does not contain parsable integer! Latch we got is:");
                System.out.println(latchReturned);
                System.out.println("");
                printOurStackFrames(n);
            }
            catch (NullPointerException p)
            {
                System.out.println("Latch returned a java null.");
                System.out.println("At url: " + driver.getCurrentUrl());
                printOurStackFrames(p);
            }
        }
        return jQcondition;
    }

    /**
     * @brief Print only stack frames that are a part of our code.
     * @param e The exception that has been caught that we want a stack trace from.
     */
    private static void printOurStackFrames(Exception e)
    {
        ArrayList<StackTraceElement> ourTrace = new ArrayList<StackTraceElement>();

        for (StackTraceElement frame : e.getStackTrace())
        {
            if (frame.getClassName().contains("com.ontraport.app"))
            {
                ourTrace.add(frame);
            }
        }
        for (StackTraceElement frame : ourTrace)
        {
            System.err.println(frame.toString());
        }
        return;
    }
}
