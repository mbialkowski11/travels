package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

public class SignUpTest extends BaseTest {


    @Test
    public void signUpTest() {


        String lastname = "Testowy";
        int randomNumber = (int) (Math.random() * 10000);

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Bartek")
                .setLastName(lastname)
                .setPhoneNumber("111222333")
                .setEmailAdress("tester" + randomNumber + "@tester.pl")
                .setPassword("Test123")
                .setConfirmPassword("Test123")
                .performSignUp();


        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastname));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Bartek Testowy");


    }

    @Test
    public void signUpTest2() {


        String lastname = "Testowy";
        int randomNumber = (int) (Math.random() * 10000);
        String email = "tester" + randomNumber + "@tester.pl";

        User user = new User();
        user.setFirstName("Bartek");
        user.setLastName("Testowy");
        user.setPhone("111111111");
        user.setEmail(email);
        user.setPassword("Test123");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        //signUpPage.fillSignUpForm("Bartek",lastname,"111111111",email,"Test123");
        signUpPage.fillSignUpForm(user);

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);


        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastname));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Bartek Testowy");


        //format ctrl + alt + L
    }

}
