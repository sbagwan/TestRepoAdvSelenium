package Page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By exploreAgenticCloud = By.xpath("//a[contains(text(),'Explore Agentic Clouds')]");
    private By blogLink = By.xpath("//a[contains(@href,'/blog')]");
    private By communityLink = By.xpath("//a[contains(@href,'community')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /** Open Home Page */
    public void openHomePage() {
        driver.get("https://www.testmu.ai/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    /** Scroll to 'Explore Agentic Clouds' safely */
    public void scrollToExploreAgenticCloud() {
        scrollToElement(exploreAgenticCloud);
    }

    /**
     * Click 'Explore Agentic Clouds' and open in a new tab safely.
     * Returns the handle of the new tab.
     */
    public String clickExploreAgenticCloudNewTab() {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(exploreAgenticCloud));
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

                // Get href
                String url = element.getAttribute("href");

                // Open in new tab via JS
                ((JavascriptExecutor) driver).executeScript("window.open(arguments[0], '_blank');", url);

                // Wait for new tab to appear
                wait.until(d -> d.getWindowHandles().size() > 1);

                // Get all window handles and return the new one
                List<String> windows = new ArrayList<>(driver.getWindowHandles());
                for (String handle : windows) {
                    if (!handle.equals(driver.getWindowHandle())) {
                        return handle; // new tab handle
                    }
                }

            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("Stale element while clicking, retry " + attempts);
            }
        }
        throw new RuntimeException("Failed to open 'Explore Agentic Clouds' in new tab after 3 attempts");
    }

    /** Navigate to Blog safely using JS click */
    public void navigateToBlog() {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement blog = new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(d -> d.findElement(blogLink));
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center'});", blog);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", blog);
                return;
            } catch (Exception e) {
                attempts++;
                System.out.println("Retry navigating to Blog, attempt " + attempts);
            }
        }
        throw new RuntimeException("Failed to navigate to Blog after retries");
    }

    /** Click Community safely using JS click */
    public void clickCommunity() {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement community = new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(d -> d.findElement(communityLink));
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center'});", community);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", community);
                return;
            } catch (Exception e) {
                attempts++;
                System.out.println("Retry clicking Community, attempt " + attempts);
            }
        }
        throw new RuntimeException("Failed to click Community after retries");
    }

    /** ===== Helper Methods ===== */
    private void scrollToElement(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("Stale element while scrolling, retry " + attempts);
            }
        }
    }
}
