package Page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BlogPage {

    WebDriver driver;
    By communityLink = By.xpath("//a[contains(text(),'Community')]");

    public BlogPage(WebDriver driver) {
        this.driver = driver;
    }

    /** Click Community link safely */
    public void clickCommunity() {
        clickCommunity(3);
    }

    public void clickCommunity(int waitSeconds) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds))
                        .until(ExpectedConditions.elementToBeClickable(communityLink));
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
                element.click();
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("Stale element while clicking Community, retry " + attempts);
            }
        }
    }
}
