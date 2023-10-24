package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SignUpHomeworkTest extends BaseTest {


    @Test
    public void signUpWithEmptyFormTest() {

        SignUpPage signUpPage = new HotelSearchPage(driver).openSignUpForm();
        signUpPage.performSignUp();


        List<String> errorNames = signUpPage.getErrorTexts();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errorNames.contains("The Email field is required."));
        softAssert.assertTrue(errorNames.contains("The Password field is required."));
        softAssert.assertTrue(errorNames.contains("The Password field is required."));
        softAssert.assertTrue(errorNames.contains("The First name field is required."));
        softAssert.assertTrue(errorNames.contains("The Last Name field is required."));
        softAssert.assertAll();


    }


    @Test
    public void signUpWithWrongEmailTest() {
        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Bartek")
                .setLastName("Testowy")
                .setPhoneNumber("111222333")
                .setEmailAdress("email")
                .setPassword("Test123")
                .setConfirmPassword("Test123");
        signUpPage.performSignUp();

        Assert.assertTrue(signUpPage.getErrorTexts().contains("The Email field must contain a valid email address."));


    }
}
