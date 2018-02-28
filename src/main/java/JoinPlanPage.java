import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JoinPlanPage {

    private WebDriver driver;

    public JoinPlanPage(WebDriver driver) {
        this.driver = driver;
    }

    private By headerText = By.xpath("//div[@class='setup-wrapper']/div/h1");


}
