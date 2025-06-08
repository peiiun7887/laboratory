package com.example.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class WebDriverConfig {

    @Bean(name = "mavenWebDriver")
    @Scope("prototype")
    public WebDriver mavenWebDriver() {
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }
}
