import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    private By signInButton = By.xpath("//a[text()='Sign in']");
    private By signUpButton = By.xpath("//a[text()='Sign up']");
    private By userNameField = By.xpath("//input[@id='user[login]']");
    private By userEmailField = By.xpath("//input[@id='user[email]']");
    private By userPassField = By.xpath("//input[@id='user[password]']");
    private By signUpFormButton = By.xpath("//button[text()='Sign up for GitHub']");
    private By headerText = By.xpath("//h1[@class='alt-h0 text-white lh-condensed-ultra mb-3']");


    public String getHeaderText(){
        return driver.findElement(headerText).getText();
    }

    public LoginPage clickSignInButton(){
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }

    public RegistrationPage clickSignUpButton(){
        driver.findElement(signUpButton).click();
        return new RegistrationPage(driver);
    }

    public RegistrationPage clickSignUpFormButton(){
        driver.findElement(signUpFormButton).click();
        return new RegistrationPage(driver);
    }

    public MainPage typeUserName(String username){
        driver.findElement(userNameField).sendKeys(username);
        return this;
    }

    public MainPage typePassFiled(String password){
        driver.findElement(userPassField).sendKeys(password);
        return this;
    }


    public MainPage typeEmailFiled(String email){
        driver.findElement(userEmailField).sendKeys(email);
        return this;
    }

    public RegistrationPage register(String username, String email, String passowrd){
        this.typeUserName(username);
        this.typeEmailFiled(email);
        this.typePassFiled(passowrd);
        clickSignUpFormButton();
        return new RegistrationPage(driver);
    }
}
