package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SignUpHomeworkTest extends BaseTest {


    @Test
    public void signUpWithEmptyFormTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.performSignUp();



        List<String> errors = signUpPage.getErrorTexts();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();


    }


    @Test
    public void signUpWithWrongEmailTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();


        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Bartek");
        signUpPage.setLastName("Testowy");
        signUpPage.setPhoneNumber("111111111");
        signUpPage.setEmailAdress("email");
        signUpPage.setPassword("Test123");
        signUpPage.setConfirmPassword("Test123");
        signUpPage.performSignUp();

        Assert.assertTrue(signUpPage.getErrorTexts().contains("The Email field must contain a valid email address."));


    }
}
