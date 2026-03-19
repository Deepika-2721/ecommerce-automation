package tests;

import pages.HomePage;
import pages.SearchResultsPage;
import utils.BaseTest;
import utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class SearchTest extends BaseTest {

    // ─────────────────────────────────────────────
    // TC_001: Verify Flipkart homepage title
    // ─────────────────────────────────────────────
    @Test(priority = 1, description = "Verify Flipkart homepage loads with correct title")
    public void verifyHomePageTitle() {
        HomePage homePage = new HomePage(driver);
        String title = homePage.getPageTitle();
        System.out.println("Page Title: " + title);
        Assert.assertTrue(title.contains("Flipkart"), "Homepage title does not contain 'Flipkart'");
    }

    // ─────────────────────────────────────────────
    // TC_002: Verify homepage URL
    // ─────────────────────────────────────────────
    @Test(priority = 2, description = "Verify Flipkart homepage URL is correct")
    public void verifyHomePageUrl() {
        HomePage homePage = new HomePage(driver);
        String url = homePage.getCurrentUrl();
        System.out.println("Current URL: " + url);
        Assert.assertTrue(url.contains("flipkart.com"), "URL does not contain flipkart.com");
    }

    // ─────────────────────────────────────────────
    // TC_003: Search for a single product
    // ─────────────────────────────────────────────
    @Test(priority = 3, description = "Search for 'laptop' and verify results are displayed")
    public void searchSingleProduct() {
        HomePage homePage = new HomePage(driver);
        SearchResultsPage resultsPage = homePage.searchProduct("laptop");
        boolean resultsDisplayed = resultsPage.areResultsDisplayed();
        System.out.println("Results displayed: " + resultsDisplayed);
        Assert.assertTrue(resultsDisplayed, "No search results displayed for 'laptop'");
    }

    // ─────────────────────────────────────────────
    // TC_004: Verify search results count > 0
    // ─────────────────────────────────────────────
    @Test(priority = 4, description = "Verify search returns more than 0 results")
    public void verifyResultCountGreaterThanZero() {
        HomePage homePage = new HomePage(driver);
        SearchResultsPage resultsPage = homePage.searchProduct("mobile phone");
        int count = resultsPage.getResultCount();
        System.out.println("Result Count: " + count);
        Assert.assertTrue(count > 0, "Result count is 0 for 'mobile phone'");
    }

    // ─────────────────────────────────────────────
    // TC_005: Verify search URL contains keyword
    // ─────────────────────────────────────────────
    @Test(priority = 5, description = "Verify URL updates with search keyword")
    public void verifySearchUrlContainsKeyword() {
        HomePage homePage = new HomePage(driver);
        SearchResultsPage resultsPage = homePage.searchProduct("headphones");
        String url = resultsPage.getPageUrl();
        System.out.println("Search URL: " + url);
        Assert.assertTrue(url.contains("headphones") || url.contains("search"),
                "URL does not reflect search keyword");
    }

    // ─────────────────────────────────────────────
    // TC_006: Verify filter section is visible
    // ─────────────────────────────────────────────
    @Test(priority = 6, description = "Verify filter/sort section appears after search")
    public void verifyFilterSectionVisible() {
        HomePage homePage = new HomePage(driver);
        SearchResultsPage resultsPage = homePage.searchProduct("shoes");
        boolean filterVisible = resultsPage.isFilterSectionVisible();
        System.out.println("Filter Visible: " + filterVisible);
        Assert.assertTrue(filterVisible, "Filter section is not visible on search results page");
    }

    // ─────────────────────────────────────────────
    // TC_007: Verify first product title is not empty
    // ─────────────────────────────────────────────
    @Test(priority = 7, description = "Verify first product title is not empty")
    public void verifyFirstProductTitle() {
        HomePage homePage = new HomePage(driver);
        SearchResultsPage resultsPage = homePage.searchProduct("watch");
        String firstTitle = resultsPage.getFirstProductTitle();
        System.out.println("First Product: " + firstTitle);
        Assert.assertFalse(firstTitle.isEmpty(), "First product title is empty");
    }

    // ─────────────────────────────────────────────
    // TC_008: Search results page title check
    // ─────────────────────────────────────────────
    @Test(priority = 8, description = "Verify search results page has a valid title")
    public void verifyResultsPageTitle() {
        HomePage homePage = new HomePage(driver);
        SearchResultsPage resultsPage = homePage.searchProduct("books");
        String title = resultsPage.getPageTitle();
        System.out.println("Results Page Title: " + title);
        Assert.assertNotNull(title, "Results page title is null");
        Assert.assertFalse(title.isEmpty(), "Results page title is empty");
    }

    // ─────────────────────────────────────────────
    // TC_009 - TC_018: Data-Driven Search (Excel)
    // ─────────────────────────────────────────────
    @Test(priority = 9, dataProvider = "searchData",
          description = "Data-driven search test using Excel - multiple keywords")
    public void dataDrivenSearchTest(String keyword) {
        System.out.println("Testing search keyword: " + keyword);
        HomePage homePage = new HomePage(driver);
        SearchResultsPage resultsPage = homePage.searchProduct(keyword);
        boolean resultsFound = resultsPage.areResultsDisplayed();
        Assert.assertTrue(resultsFound,
                "No results found for keyword: " + keyword);
    }

    @DataProvider(name = "searchData")
    public Object[][] getSearchData() throws IOException {
        // Falls back to hardcoded data if Excel not present
        // Replace file path with your actual Excel file path after downloading
        try {
            ExcelUtils.loadExcel(
                "src/test/resources/SearchTestData.xlsx", "SearchData");
            int rowCount = ExcelUtils.getRowCount();
            Object[][] data = new Object[rowCount][1];
            for (int i = 1; i <= rowCount; i++) {
                data[i - 1][0] = ExcelUtils.getCellData(i, 0);
            }
            ExcelUtils.closeWorkbook();
            return data;
        } catch (Exception e) {
            System.out.println("Excel file not found, using hardcoded data: " + e.getMessage());
            return new Object[][]{
                {"laptop"}, {"mobile phone"}, {"headphones"}, {"smartwatch"},
                {"shoes"}, {"camera"}, {"tablet"}, {"keyboard"}, {"monitor"}, {"backpack"}
            };
        }
    }
}
