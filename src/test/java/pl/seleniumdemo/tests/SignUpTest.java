package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

public class SignUpTest extends BaseTest {


    @Test
    public void signUpTest() {

        int randomNumber = (int) (Math.random() * 10000);
        String email = "tester" + randomNumber + "@tester.pl";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Bartek");
        signUpPage.setLastName("Testowy");
        signUpPage.setPhoneNumber("111111111");
        signUpPage.setEmailAdress(email);
        signUpPage.setPassword("Test123");
        signUpPage.setConfirmPassword("Test123");
        signUpPage.performSignUp();


        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        Assert.assertTrue(loggedUserPage.getHeadingText().contains("Testowy"));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Bartek Testowy");


    }

}
