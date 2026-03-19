package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(name = "q")
    WebElement searchBox;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;

    // Login popup close button (Flipkart shows login popup on load)
    @FindBy(xpath = "//button[contains(text(),'✕')]")
    WebElement closeLoginPopup;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void closeLoginPopupIfPresent() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeLoginPopup));
            closeLoginPopup.click();
        } catch (Exception e) {
            // Popup not present, continue
        }
    }

    public SearchResultsPage searchProduct(String keyword) {
        closeLoginPopupIfPresent();
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(keyword);
        searchBox.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
