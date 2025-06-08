package com.example.controller;

import com.example.config.UserComponent;
import com.example.dto.RowWithPrice;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectController {



    @Autowired
    @Qualifier(value = "mavenWebDriver")
    WebDriver driver;

    @Autowired
    UserComponent component;

    @GetMapping(path = "/start")
    public void start() {

        // Validate WebDriver session
        if (driver == null || ((ChromeDriver) driver).getSessionId() == null) {
            System.out.println("Session is invalid. Reinitializing WebDriver...");
            driver = new ChromeDriver();
        }

        // Step 1
        driver.get(component.getTarget());
        driver.findElement(By.cssSelector("#PerformanceListTable tr:nth-child(2) #buy_btn")).click();

        // Step 2
        List<WebElement> rows = driver.findElements(By.xpath("//div[contains(@class,'seats')]//table[contains(@class,'f1')]//tbody//tr[contains(@class,'saleTr')]"));
        List<RowWithPrice> rowWithPricesList = new ArrayList<>();
        for (WebElement row : rows) {
            String amount = row.getAttribute("amount");
            List<WebElement> tds = row.findElements(By.tagName("td"));
            boolean isUnabled = tds.size() >= 2 && tds.get(1).getText().contains("輪椅");

            if ("0".equals(amount) || isUnabled) continue;
            WebElement textPriceElement = row.findElement(By.className("textPrice"));
            String priceText = textPriceElement.getText().replaceAll("[^\\d]", "");
            int price = 0;
            try {
                price = Integer.parseInt(priceText);
            } catch (NumberFormatException e) {
                continue;
            }
            rowWithPricesList.add(new RowWithPrice(row, price));
        }
        rowWithPricesList.sort((a, b) -> Integer.compare(a.getPrice(), b.getPrice()));
        if (!rowWithPricesList.isEmpty()) {
            rowWithPricesList.get(0).getRow().click();
        } else {
            System.out.println("沒有可用的區域");
        }

        // Step 3
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", driver.findElement(By.id("IMG_THUMB")));
        driver.findElement(By.cssSelector("div.ticket.flex.f1 > button.f1.green")).click();
        // 取得所有有 title 屬性的 td
        List<WebElement> tdList = driver.findElements(By.xpath("//*[@id='TBL']//tbody//tr/td[@title]"));
        long currentTimeseat = System.currentTimeMillis();
        for (WebElement td : tdList) {
            if (!td.getAttribute("title").isEmpty()) {
                System.out.println("選擇位置: " + td.getAttribute("title"));
                // 可根據需求點擊
                td.click();
                break;
            }
        }
        System.out.println("選位花費時間: " + (System.currentTimeMillis() - currentTimeseat) + "ms");

        try{
            WebElement account = driver.findElement(By.id("LOGIN_ID"));
            WebElement pwd = driver.findElement(By.id("LOGIN_PWD"));
            account.sendKeys(component.getAccount());
            pwd.sendKeys(component.getPwd());
        }catch (Exception e) {
            System.out.println("欄位不存在");
        }

        while(driver.findElement(By.id("CHK")).getAttribute("value")== null || driver.findElement(By.id("CHK")).getAttribute("value").length()<4) {
            System.out.println("waiting for captcha...");
            try {
                Thread.sleep(200); // 等待0.2秒後再檢查
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 恢復中斷狀態
            }
        }
        driver.findElement(By.id("addcart")).click();
    }

}
