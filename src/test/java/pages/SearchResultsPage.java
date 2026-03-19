package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[@class='_1AtVbE col-12-12']//div[@class='_4rR01T']")
    List<WebElement> productTitles;

    @FindBy(xpath = "//div[contains(text(),'Brand')]")
    WebElement brandFilter;

    @FindBy(xpath = "//div[@class='_2kHMtA']")
    List<WebElement> searchResults;

    // Sort dropdown
    @FindBy(xpath = "//div[contains(@class,'_1l_aED')]")
    WebElement sortDropdown;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public boolean areResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
            return searchResults.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public int getResultCount() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
            return searchResults.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getFirstProductTitle() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(productTitles));
            return productTitles.get(0).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isKeywordPresentInResults(String keyword) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(productTitles));
            for (WebElement title : productTitles) {
                if (title.getText().toLowerCase().contains(keyword.toLowerCase())) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickOnFirstProduct() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(productTitles));
            productTitles.get(0).click();
        } catch (Exception e) {
            System.out.println("Could not click first product: " + e.getMessage());
        }
    }

    public boolean isFilterSectionVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(brandFilter));
            return brandFilter.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
