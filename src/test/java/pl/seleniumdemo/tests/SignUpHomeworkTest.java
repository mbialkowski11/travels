package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.SignUpPage;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class SignUpHomeworkTest extends BaseTest {


    @Test
    public void signUpWithEmptyFormTest() throws IOException {

        ExtentTest test = extentReports.createTest("Sign Up With Empty Form Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        test.log(Status.PASS, "Open sign up form done", SeleniumHelper.getScreenshot(driver));

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.performSignUp();

        test.log(Status.PASS, "Perform sign up done", SeleniumHelper.getScreenshot(driver));



        List<String> errors = signUpPage.getErrorTexts();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();

        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));


    }


    @Test
    public void signUpWithWrongEmailTest() throws IOException {

        ExtentTest test = extentReports.createTest("Sign Up With Wrong Email Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        test.log(Status.PASS, "Open sign up form done", SeleniumHelper.getScreenshot(driver));


        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Bartek");
        signUpPage.setLastName("Testowy");
        signUpPage.setPhoneNumber("111111111");
        signUpPage.setEmailAdress("email");
        signUpPage.setPassword("Test123");
        signUpPage.setConfirmPassword("Test123");
        test.log(Status.PASS, "Fill labels", SeleniumHelper.getScreenshot(driver));
        signUpPage.performSignUp();
        test.log(Status.PASS, "Perform sign up done", SeleniumHelper.getScreenshot(driver));

        Assert.assertTrue(signUpPage.getErrorTexts().contains("The Email field must contain a valid email address."));
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));


    }
}
