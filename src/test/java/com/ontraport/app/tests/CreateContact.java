package com.ontraport.app.tests;

import org.testng.annotations.Test;

import com.ontraport.app.pages.Contact_Edit;
import com.ontraport.app.pages.Contact_ListAll;
import com.ontraport.app.tools.AbstractTest;

/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/24/16
 */
public class CreateContact extends AbstractTest
{
    @Test
    public void testCreateContact() throws InterruptedException
    {
        Contact_ListAll contact_listAll = new Contact_ListAll(driver);

        Contact_Edit contact_edit = contact_listAll.clickNewContact();
        contact_edit.enterFieldValue("First Name", "Test");
        contact_edit.enterFieldValue("Last Name", "Contact");
        contact_edit.enterFieldValue("Email", UNIQUE + "@example.com");

        contact_listAll = contact_edit.clickBack();
//        contact_listAll.search.find(UNIQUE + "@example.com");

        assert contact_listAll.verifyContactExists(UNIQUE + "@example.com") : "Contact not found";

        Thread.sleep(3000);
    }
}