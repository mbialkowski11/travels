package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {

    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(name = "phone")
    private WebElement phoneNumberInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> errorList;

    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public SignUpPage setFirstName(String addFirstName) {
        firstNameInput.sendKeys(addFirstName);
        return this;
    }

    public SignUpPage setLastName(String addLastName) {
        lastNameInput.sendKeys(addLastName);
        return this;
    }

    public SignUpPage setPhoneNumber(String addPhoneNumber) {
        phoneNumberInput.sendKeys(addPhoneNumber);
        return this;
    }

    public SignUpPage setEmailAdress(String addEmailAdress) {
        emailInput.sendKeys(addEmailAdress);
        return this;
    }

    public SignUpPage setPassword(String addPassword) {
        passwordInput.sendKeys(addPassword);
        return this;
    }

    public SignUpPage setConfirmPassword(String addConfirmPassword) {
        confirmPasswordInput.sendKeys(addConfirmPassword);
        return this;
    }

    public LoggedUserPage performSignUp() {
        signUpButton.click();
        return new LoggedUserPage(driver);
    }

    public List<String> getErrorTexts() {
        return errorList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

    }


}