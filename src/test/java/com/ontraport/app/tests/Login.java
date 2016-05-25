package com.ontraport.app.tests;

import org.testng.annotations.Test;

import com.ontraport.app.pages.LandingPage_ListAll;
import com.ontraport.app.pages.OntrapagesLogin;
import com.ontraport.app.tools.AbstractTest;

/**
 * A sample test to show off logging in
 *
 * @author jason
 * @since 5/24/16
 */
public class Login extends AbstractTest
{
    @Test
    public void testLogin ()
    {
        OntrapagesLogin ontrapagesLogin = new OntrapagesLogin(driver);

        LandingPage_ListAll landingPage_listAll = ontrapagesLogin.loginAs("foo", "bar");

        assert landingPage_listAll.verifyPage() == false : "Didn't end up on LandingPage ListAll";
    }
}
