package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;

public class SignUpTest extends BaseTest {


    @Test
    public void signUpTest() throws IOException {

        ExtentTest test = extentReports.createTest("Sign Up Test");

        int randomNumber = (int) (Math.random() * 10000);
        String email = "tester" + randomNumber + "@tester.pl";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        test.log(Status.PASS, "Open sign up form done", SeleniumHelper.getScreenshot(driver));

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Bartek");
        signUpPage.setLastName("Testowy");
        signUpPage.setPhoneNumber("111111111");
        signUpPage.setEmailAdress(email);
        signUpPage.setPassword("Test123");
        signUpPage.setConfirmPassword("Test123");
        test.log(Status.PASS, "Fill labels", SeleniumHelper.getScreenshot(driver));
        signUpPage.performSignUp();
        test.log(Status.PASS, "Perform sign up done", SeleniumHelper.getScreenshot(driver));


        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        Assert.assertTrue(loggedUserPage.getHeadingText().contains("Testowy"));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Bartek Testowy");
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));


    }

}
