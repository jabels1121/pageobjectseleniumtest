import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private By heading = By.xpath("//div[@class='setup-wrapper']//h1");
    private By userNameField = By.xpath("//input[@id='user_login']");
    private By userEmailField = By.xpath("//input[@id='user_email']");
    private By userPassField = By.xpath("//input[@id='user_password']");
    private By createAccButton = By.xpath("//button[@id='signup_button']");
    private By signInButton = By.xpath("//a[text()='Sign in']");
    private By signUpButton = By.xpath("//a[text()='Sign up']");
    private By errorLoginBlank = By.xpath("//input[@id='user_login']/ancestor::dd/following-sibling::dd");
    private By errorEmailBlank = By.xpath("//input[@id='user_email']/ancestor::dd/following-sibling::dd");
    private By errorPassBlank = By.xpath("//input[@id='user_password']/ancestor::dd/following-sibling::dd");
    private By errorWithCreateAcc = By.xpath("//div[@class='flash flash-error my-3']");
    private By logoInHeader = By.xpath("//a[@class='header-logo-invertocat my-0']");

    public MainPage clickLogo(){
        driver.findElement(logoInHeader).click();
        return new MainPage(driver);
    }

    public String getErrorHeadText(){
        return driver.findElement(errorWithCreateAcc).getText();
    }

    public String getErrorLoginText(){
        return driver.findElement(errorLoginBlank).getText();
    }


    public String getErrorEmailText(){
        return driver.findElement(errorEmailBlank).getText();
    }

    public String getErrorPasslText(){
        return driver.findElement(errorPassBlank).getText();
    }

    public String getHeadingText(){
        return driver.findElement(heading).getText();
    }

    public RegistrationPage typeLogin(String login){
        driver.findElement(userNameField).sendKeys(login);
        return this;
    }

    public RegistrationPage typeEmail(String email){
        driver.findElement(userEmailField).sendKeys(email);
        return this;
    }

    public RegistrationPage typePassword(String password){
        driver.findElement(userPassField).sendKeys(password);
        return this;
    }

    public RegistrationPage createAccWithIncorrectCreds(){
        driver.findElement(createAccButton).click();
        return new RegistrationPage(driver);
    }

    public RegistrationPage clickSignUpButton(){
        driver.findElement(signUpButton).click();
        return new RegistrationPage(driver);
    }

    public LoginPage clickSignInButton(){
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }

    public RegistrationPage failureRegistration(String login, String email, String password){
        typeLogin(login);
        typeEmail(email);
        typePassword(password);
        driver.findElement(createAccButton).click();
        return new RegistrationPage(driver);
    }

    public JoinPlanPage successRegistration(String login, String email, String password){
        typeLogin(login);
        typeEmail(email);
        typePassword(password);
        driver.findElement(createAccButton).click();
        return new JoinPlanPage(driver);
    }

}
