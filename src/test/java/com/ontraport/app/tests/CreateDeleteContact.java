package com.ontraport.app.tests;

import org.testng.annotations.Test;

import com.ontraport.app.pages.Contact_ListAll;
import com.ontraport.app.tools.AbstractTest;

/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/24/16
 */
public class CreateDeleteContact extends AbstractTest
{
    @Test
    public void testCreateDeleteContact()
    {
        Contact_ListAll contact_listAll = new Contact_ListAll(driver);
        Contact_Edit contact_edit = contact_listAll.clickNewContact();
    }
}
