package com.phonebook.tests;
import com.phonebook.data.ContactData;
import com.phonebook.data.UserData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import com.phonebook.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.naming.Name;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }

        app.getUser().clickOnLoginLinc();
        app.getUser().fillRegisterLoginForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addContactPositiveTest() {

        app.getContact().clickOnAddLink();
        app.getContact().fiiContactForm(new Contact()
                .setName(ContactData.NAME)
                .setLastName(ContactData.LAST_NAME)
                .setPhone(ContactData.PHONE)
                .setEmail(ContactData.EMAIL)
                .setAddress(ContactData.ADDRESS)
                .setDescription(ContactData.DESC));
        app.getContact().clickOnSafeButton();
        Assert.assertTrue(app.getContact().isContactAdded(ContactData.NAME));

    }


    @Test(dataProvider = "addNewContact",dataProviderClass = DataProviders.class)
    public void addContactPositiveFromDataProviderTest(String name, String lastName, String phone,
                                                       String email, String address, String desc) {

        app.getContact().clickOnAddLink();
        app.getContact().fiiContactForm(new Contact()
                .setName(name)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(desc));
        app.getContact().clickOnSafeButton();
        Assert.assertTrue(app.getContact().isContactAdded(name));

    }




    @Test(dataProvider = "addNewContactWithCvs",dataProviderClass = DataProviders.class)
    public void addContactPositiveFromDataProviderWithCsvTest(Contact contact
                                                       ) {

        app.getContact().clickOnAddLink();
        app.getContact().fiiContactForm(contact);
        app.getContact().clickOnSafeButton();
        Assert.assertTrue(app.getContact().isContactAdded(contact.getName()));

    }

    @AfterMethod
    public void postCondition(){
        app.getContact().deleteContact();
    }

}