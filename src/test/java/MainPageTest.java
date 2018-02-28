import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MainPageTest {

    private   WebDriver driver1;
    private MainPage mainPage;


    @Before
    public void setUp(){
        if (driver1 != null){
            return;
        }
        driver1 = new ChromeDriver();
        driver1.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver1.manage().window().maximize();
        driver1.get("http://github.com");
        mainPage = new MainPage(driver1);

    }

    @Test
    //@Ignore
    public void signUpButtonTest(){
        RegistrationPage registrationPage = mainPage.clickSignUpButton();
        String heading = registrationPage.getHeadingText();
        Assert.assertEquals("Join GitHub", heading);
    }

    @Test
    public void singInButtonTest(){
        LoginPage loginPage = mainPage.clickSignInButton();
        String heading = loginPage.getHeadingText();
        Assert.assertEquals("Sign in to GitHub", heading);
    }

    @Test
    //@Ignore
    public void registerFailTest(){
        RegistrationPage registrationPage = mainPage.register("testuser", "testuser@mail.ru", "123321123qwe");
        String error = registrationPage.getErrorHeadText();
        Assert.assertEquals("There were problems creating your account.", error);
    }

    @Test
    public void singUpEmptyUserNameTest(){
        RegistrationPage registrationPage = mainPage.register("", "testuser@mail.ru", "123123qweqwe");
        String error = registrationPage.getErrorLoginText();
        Assert.assertEquals("Login can't be blank", error);
    }

    @Test
    public void singUpInvalidEmailTest(){
        RegistrationPage registrationPage = mainPage.register("testetstetste", "tes", "123123qweqwe");
        String error = registrationPage.getErrorEmailText();
        Assert.assertEquals("Email is invalid or already taken", error);
    }

    @After
    public void tearDown(){
        driver1.quit();
    }


}
