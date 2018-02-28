import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginField = By.xpath("//input[@id='login_field']");
    private By passwordFiled = By.xpath("//input[@id='password']");
    private By signInButton = By.xpath("//input[@type='submit']");
    private By headingText = By.xpath("//*[@id='login']/form/div[2]/h1");
    private By headerText = By.xpath("//div[@class='auth-form-header p-0']/h1");
    private By error = By.xpath("//div[@class='container']");
    private By createAnAccountLink = By.xpath("//p[@class='create-account-callout mt-3']/a");

    public String getHeadingText(){
        return driver.findElement(headingText).getText();
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }


    public LoginPage typeLogin(String login){
        driver.findElement(loginField).sendKeys(login);
        return this;
    }

    public LoginPage typePassword(String password){
        driver.findElement(passwordFiled).sendKeys(password);
        return this;
    }

    public LoginPage loginWithIncorrectCreds(String username, String password){
        this.typeLogin(username);
        this.typePassword(password);
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }

    public String getErrorText(){
        return driver.findElement(error).getText();
    }

    public RegistrationPage clickCreateAnAccountLink(){
        driver.findElement(createAnAccountLink).click();
        return new RegistrationPage(driver);
    }

}
