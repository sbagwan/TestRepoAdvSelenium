package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignUpPage {

    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    /** Get title for cloud-safe assertion */
    public String getPageTitle() {
        return getPageTitle(3);
    }

    public String getPageTitle(int waitSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(waitSeconds))
                    .until((ExpectedCondition<Boolean>) d -> !d.getTitle().isEmpty());
        } catch (Exception e) {
            // ignore timeout, return whatever title is present
        }
        return driver.getTitle();
    }
}
