package com.ontraport.app.tests;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.ontraport.app.pages.QATest_Home;
import com.ontraport.app.pages.QATest_Pricing;
import com.ontraport.app.tools.AbstractTest;
import com.ontraport.app.tools.AbstractTest.Account;
import com.ontraport.app.tools.AbstractTest.Header;
import com.ontraport.app.tools.Categories.Grid;

public class TestSignup extends AbstractTest
{
    @Test
    public void testSignUp ()
    {
    	driver.get("http://qatest.pages.ontraport.net/");
    	QATest_Home qATest_Home = new QATest_Home();
    	qATest_Home.enterFirstName("Ted");
    	qATest_Home.enterLastName("Wang");
    	qATest_Home.enterEmail("samfengyuwang@gmail.com");
    	qATest_Home.enterPassword("123456789");
    	qATest_Home.enterConfPassword("123456789");
    	qATest_Home.clickSubmit();
    	
    	if(!driver.getPageSource().contains("http://qatest.pages.ontraport.net/pricing")){
    		fail("Did not redirect to the pricing page as expected");
    	}
    	
    	QATest_Pricing qATest_Pricing = new QATest_Pricing();
    	if(qATest_Pricing.verifyPrice("5,970") == false){
    		fail("5970 is not in the table as expected");
    	}

    	
    }
}

