package com.ontraport.app.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ontraport.app.tools.AbstractPage;

public class QATest_Pricing extends AbstractPage
{
	
	@FindBy(css = "table[class*='pricing-table']")
    public WebElement pricingTable;
	
	
	public boolean verifyPrice ( String price )
    {
    	String s = "table[class*='pricing-table'] tr";        	   	
    	List <WebElement> row_Price =   driver.findElements(By.cssSelector(s));
    	Boolean flag = false;
    	
    	for( WebElement e : row_Price )
    	{
    		if( e.getText().contains(price) )
    		{
    			flag = true;
    			break;
    		}
    	}
    	
    	return flag;
    }  

}