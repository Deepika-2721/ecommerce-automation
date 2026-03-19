# E-Commerce Web Application Automation Framework

## Project Overview
End-to-end Selenium automation framework for an e-commerce web application (Flipkart).
Covers product search, filter, cart workflows, and cross-browser compatibility testing.

## Tech Stack
- Java 11
- Selenium WebDriver 4.18.1
- TestNG 7.9.0
- Maven
- Apache POI (Excel - data-driven testing)
- WebDriverManager
- Page Object Model (POM)

## Test Scenarios Covered (25+ test cases)
- Homepage title and URL validation
- Product search with keyword validation
- Search result count verification
- URL update verification after search
- Filter section visibility
- First product title validation
- Data-driven search with 30+ keywords from Excel
- Cross-browser execution: Chrome, Firefox, Edge

## Project Structure
```
ecommerce-automation/
├── src/test/java/
│   ├── pages/
│   │   ├── HomePage.java
│   │   └── SearchResultsPage.java
│   ├── tests/
│   │   └── SearchTest.java
│   └── utils/
│       ├── BaseTest.java
│       └── ExcelUtils.java
├── src/test/resources/
│   └── SearchTestData.xlsx   ← Add your Excel file here
├── testng.xml
└── pom.xml
```

## How to Run
1. Clone the repo:
   ```
   git clone https://github.com/[YOUR-GITHUB-USERNAME]/ecommerce-automation
   ```
2. Open in Eclipse or IntelliJ as a Maven project
3. Maven will auto-download all dependencies
4. Run: `mvn test` OR right-click `testng.xml` → Run As TestNG Suite
5. View HTML report: `test-output/index.html`

## Excel Test Data Format
Create `SearchTestData.xlsx` in `src/test/resources/` with sheet name `SearchData`:

| Column A   |
|------------|
| laptop     |
| mobile phone |
| headphones |
| ... (add more keywords) |

## Defects Found
| ID    | Description                              | Severity | Priority |
|-------|------------------------------------------|----------|----------|
| BUG-01 | Filter count mismatch on Firefox        | Medium   | High     |
| BUG-02 | Search URL doesn't update for short keywords | Low | Medium |
| BUG-03 | Result count fluctuates on Edge         | Low      | Low      |

## Author
Deepika M | deepikamvel27@gmail.com
