package Page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AgenticCloudPage {

    WebDriver driver;
    By scaleSection = By.xpath("//h2[contains(text(),'Seamlessly Scale with Agentic Cloud')]");
    By tryNowForFree = By.xpath("//a[contains(text(),'Try Now For Free')]");

    public AgenticCloudPage(WebDriver driver) {
        this.driver = driver;
    }

    /** Scroll to scale section safely */
    public void scrollToScaleSection() {
        scrollToElement(scaleSection, 3);
    }

    public void scrollToScaleSection(int waitSeconds) {
        scrollToElement(scaleSection, waitSeconds);
    }

    /** Click 'Try Now For Free' safely */
    public void clickTryNowForFree() {
        clickElementStaleSafe(tryNowForFree, 3);
    }

    public void clickTryNowForFree(int waitSeconds) {
        clickElementStaleSafe(tryNowForFree, waitSeconds);
    }

    /** Stale-safe scroll */
    private void scrollToElement(By locator, int waitSeconds) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds))
                        .until(ExpectedConditions.visibilityOfElementLocated(locator));
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("Stale element while scrolling, retry " + attempts);
            }
        }
    }

    /** Stale-safe click */
    private void clickElementStaleSafe(By locator, int waitSeconds) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds))
                        .until(ExpectedConditions.elementToBeClickable(locator));
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
                element.click();
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("Stale element while clicking, retry " + attempts);
            }
        }
    }
}
