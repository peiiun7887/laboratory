

package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(WebDriverConfig.class)
public class GoogleSearchTest {

    @Autowired
    private WebDriver driver;

//    @Test
    public void testGoogleTitle() {
        // Navigate to Google
        driver.get("https://www.google.com");

        // Verify the page title
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);
        assertTrue(pageTitle.contains("Google"), "Page title should contain 'Google'");
    }

    @AfterEach
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}