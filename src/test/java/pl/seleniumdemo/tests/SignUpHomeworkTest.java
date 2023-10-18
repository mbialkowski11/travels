package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.tests.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpHomeworkTest extends BaseTest {


    @Test
    public void signUpWithEmptyFormTest() {

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();


        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream()
                                                                                .map(WebElement::getText)
                                                                                .collect(Collectors.toList());

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

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        String lastname = "Testowy";
        driver.findElement(By.name("firstname")).sendKeys("Bartek");
        driver.findElement(By.name("lastname")).sendKeys(lastname);
        driver.findElement(By.name("phone")).sendKeys("111111111");
        driver.findElement(By.name("email")).sendKeys("tester.pl");
        driver.findElement(By.name("password")).sendKeys("Test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test123");

        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertTrue(errors.contains("The Email field must contain a valid email address."));


    }
}
