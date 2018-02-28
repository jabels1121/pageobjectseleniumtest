import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class RegistrationPageTest {

    private WebDriver driver3;
    private RegistrationPage registrationPage;

    @Before
    public void setUp() {
        driver3 = new ChromeDriver();
        driver3.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver3.manage().window().maximize();
        driver3.get("https://github.com/join");
        registrationPage = new RegistrationPage(driver3);
    }

    @Test
    public void regWithEmptyCredsTest(){
        RegistrationPage newRegistrationPage = registrationPage.createAccWithIncorrectCreds();
        String error = newRegistrationPage.getErrorHeadText();
        Assert.assertEquals("There were problems creating your account.", error);
    }

    @Test
    public void regReservedUsernameTest(){
        RegistrationPage rp = registrationPage.typeLogin("username");
        String error = rp.getErrorLoginText();
        Assert.assertEquals("Username name 'username' is a reserved word", error);
    }

    @Test
    public void regTakenUsernameTest(){
        RegistrationPage rp = registrationPage.typeLogin("user");
        String error = rp.getErrorLoginText();
        Assert.assertEquals("Username is already taken", error);
    }

    @Test
    public void regWithEmptyUserNameTest(){
        RegistrationPage newRegistrationPage = registrationPage.failureRegistration("", "test@mail.ru", "123123qweqwe");
        String error = newRegistrationPage.getErrorLoginText();
        Assert.assertEquals("Login can't be blank", error);
    }

    @Test
    public void regWithEmptyEmailTest(){
        RegistrationPage newRegistrationPage = registrationPage.failureRegistration("tetetetrte123", "", "123123qweqwe");
        String error = newRegistrationPage.getErrorEmailText();
        Assert.assertEquals("Email can't be blank", error);
    }

    @Test
    public void regWithEmptyPasswordTest(){
        RegistrationPage newRegistrationPage = registrationPage.failureRegistration("tetetetrte123", "test@mail.ru", "");
        String error = newRegistrationPage.getErrorPasslText();
        Assert.assertEquals("Password can't be blank and is too short (minimum is 7 characters)", error);
    }

    @Test
    public void clickSignInButtonTest(){
        LoginPage loginPage = registrationPage.clickSignInButton();
        String title = loginPage.getUrl();
        String header = loginPage.getHeadingText();
        Assert.assertEquals("Sign in to GitHub", header);
    }

    @Test
    public void clickSignUpButtonTest(){
        RegistrationPage newRegistrationPage = registrationPage.clickSignUpButton();
        String header = registrationPage.getHeadingText();
        Assert.assertEquals("Join GitHub", header);
    }

    @Test
    public void incorrectEmailCredsTest(){
        RegistrationPage newRegistrationPage = registrationPage.failureRegistration("huyhuyhuhyhuy13", "test", "1223123qweqwdq");
        String errorEmailField = newRegistrationPage.getErrorEmailText();
        Assert.assertEquals("Email is invalid or already taken", errorEmailField);
    }

    @Test
    public void passWithOblyNumbersTest(){
        RegistrationPage newRegistrationPage = registrationPage.failureRegistration("qweqwe123123qwe", "test@test.rest", "12345678");
        String errorPassWithOnlyNumbers = registrationPage.getErrorPasslText();
        Assert.assertEquals("Password needs at least one lowercase letter", errorPassWithOnlyNumbers);
    }

    @Test
    public void clickLogoTest(){
        MainPage mainPage = registrationPage.clickLogo();
        String headerText = mainPage.getHeaderText();
        Assert.assertEquals("Built for developers", headerText);
    }

    @Test
    public void succesRegistratonTest(){
        JoinPlanPage joinPlanPage = registrationPage.successRegistration("testusertesrq", "test@mail.ru", "test123qwe321");

    }

    @After
    public void tearDown(){
        driver3.quit();
    }
}
