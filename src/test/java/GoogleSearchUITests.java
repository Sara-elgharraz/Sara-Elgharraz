import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


public class GoogleSearchUITests {

        private WebDriver driver;

        @BeforeClass
        public void setUp() {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Breadfast\\Desktop\\New folder\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        @Test
        public void testBasicSearchFunctionality() {
            // Test Case 1: Basic Search Functionality
            driver.get("https://www.google.com");
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("cats");
            searchBox.submit();
            // Verify that search results are displayed
            Assert.assertTrue(driver.getTitle().toLowerCase().contains("cats"));
        }

        @Test
        public void testAdvancedSearchOperators() {
            // Test Case 2: Advanced Search Operators
            driver.get("https://www.google.com");
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("site:wikipedia.org cats");
            searchBox.submit();
            // Verify that search results are limited to Wikipedia
            Assert.assertTrue(driver.getPageSource().contains("wikipedia.org"));
        }



        @Test
        public void testImageSearch() {
            // Test Case 3: Image Search
            driver.get("https://www.google.com/imghp");
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("sunset");
            searchBox.submit();

            // Get a list of image elements
            List<WebElement> imageResults = driver.findElements(By.cssSelector(".rg_i"));


            // Verify image search results
           Assert.assertTrue(driver.findElements(By.cssSelector(".rg_i")).size() > 0);
           By imageXPath = By.xpath("//*[@id='islrg']/div[1]/div[1]/a[1]/div[1]/img");
           Assert.assertTrue(driver.findElements(imageXPath).size() > 0);     
        }


        @AfterClass
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }


