package tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Page.*;

import java.util.*;

public class ChromeWindowsTest extends BaseTest {

    @Test(timeOut = 20000) // 20 seconds max per test
    public void testScenarioChromeWindows() {

        // 1️⃣ Open homepage
        HomePage home = new HomePage(driver);
        home.openHomePage();

        // 2️⃣ Scroll to 'Explore Agentic Clouds'
        home.scrollToExploreAgenticCloud();

        // 3️⃣ Click 'Explore Agentic Clouds' → open in new tab
        String newTabHandle = home.clickExploreAgenticCloudNewTab();

        // 4️⃣ Verify new tab opened
        Set<String> handles = driver.getWindowHandles();
        Assert.assertTrue(handles.size() > 1, "New tab was not opened!");
        System.out.println("Window handles: " + handles);

        // 5️⃣ Switch to new tab
        driver.switchTo().window(newTabHandle);

        // 6️⃣ Verify URL contains 'agentic'
        String agenticURL = driver.getCurrentUrl();
        Assert.assertTrue(agenticURL.contains("agentic"),
                "Expected URL to contain 'agentic', Actual: " + agenticURL);

        // 7️⃣ Scroll to scale section and click 'Try Now For Free'
        AgenticCloudPage agentic = new AgenticCloudPage(driver);
        agentic.scrollToScaleSection();
        agentic.clickTryNowForFree();

        // 8️⃣ Verify Sign Up page title
        SignUpPage signUp = new SignUpPage(driver);
        String signUpTitle = signUp.getPageTitle();
        Assert.assertTrue(signUpTitle.contains("Sign up for free | Cross Browser Testing Tool"),
                "Sign Up page title mismatch. Actual: " + signUpTitle);

        // 9️⃣ Close Sign Up tab
        driver.close();

        // 🔟 Switch back to main window
        String mainHandle = handles.iterator().next();
        driver.switchTo().window(mainHandle);
        System.out.println("Current window count: " + driver.getWindowHandles().size());

        // 1️⃣1️⃣ Navigate to Blog safely
        home.navigateToBlog(); // Uses JS click + retries

        // 1️⃣2️⃣ Click Community safely
        home.clickCommunity(); // Uses JS click + retries

        // 1️⃣3️⃣ Verify Community URL
       // String actualCommunityURL = driver.getCurrentUrl();
        String communityURL = driver.getCurrentUrl();
        boolean validCommunityURL = communityURL.contains("/community") || communityURL.contains("community.testmuai.com");
        Assert.assertTrue(validCommunityURL, "Community page URL mismatch. Actual: " + communityURL);

        // Close main window
        driver.close();
    }
}
