import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {

    private WebDriver driver2;
    private LoginPage loginPage;


    @Before
    public void setUp() {
        driver2 = new ChromeDriver();
        driver2.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver2.manage().window().maximize();
        driver2.get("https://github.com/login");
        loginPage = new LoginPage(driver2);
    }

    @Test
    public void loginWithEmptyCredsTest(){
        LoginPage newLoginPage = loginPage.loginWithIncorrectCreds("", "");
        String error = newLoginPage.getErrorText();
        Assert.assertEquals("Incorrect username or password.", error);
    }

    @Test
    public void loginWithIncorrectCredsTesr(){
        LoginPage newLoginPage = loginPage.loginWithIncorrectCreds("evoineveveiovmeimv", "svsmvoseimvoeimv");
        String error = newLoginPage.getErrorText();
        Assert.assertEquals("Incorrect username or password.", error);
    }

    @Test
    public void clickCreateAnAccLinkTest(){
        RegistrationPage registrationPage = loginPage.clickCreateAnAccountLink();
        String heading = registrationPage.getHeadingText();
        Assert.assertEquals("Join GitHub", heading);
    }

    @After
    public void tearDown(){
        driver2.quit();
    }

}
