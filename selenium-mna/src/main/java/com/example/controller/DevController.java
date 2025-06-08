package com.example.controller;

import com.example.dto.RowWithPrice;
import com.twocaptcha.TwoCaptcha;
import com.twocaptcha.captcha.Normal;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class DevController {

    public static void main(String[] args) throws URISyntaxException, IOException {
        // launch the browser
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver driver = new ChromeDriver(chromeOptions);
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver-win64\\chromedriver.exe");


        // Step 1: 進入節目頁面，點擊購買按鈕
        driver.get("https://ticket.mna.com.tw/UTK0201_?PRODUCT_ID=P0W6SG9Q");
        //https://ticket.mna.com.tw/UTK0201_?PRODUCT_ID=P0WQSOJ8
        driver.findElement(By.cssSelector("#PerformanceListTable tr:nth-child(2) #buy_btn")).click();

        // Step 2: 收集頁面上的票區清單，篩選還有剩餘空位且不是輪椅位的票區，依價格排序票區，選取最低價的票區，進入選位畫面
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



//        Thread captchaThread = new Thread(() -> {
//
//            while(driver.findElement(By.id("chk_pic")) == null) {
//
//                System.out.println("pic not found, waiting...");
//                try {
//                    Thread.sleep(200); // 等待1秒後再檢查
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt(); // 恢復中斷狀態
//                }
//            }
//            WebElement picElement = driver.findElement(By.id("chk_pic"));
//            String prefix = picElement.getAttribute("src").substring(59);
//            System.out.println(prefix);
//            String picPath = "E:\\laboratory\\selenium-mna\\src\\main\\java\\com\\example\\captcha\\captcha_"+prefix+".png";
//            long currentTimePic = System.currentTimeMillis();
//            File filesource = picElement.getScreenshotAs(OutputType.FILE);
//            File filetarget = new File(picPath);
//            filesource.renameTo(filetarget);
//            System.out.println("pic saved spend time: "+(System.currentTimeMillis()-currentTimePic)+"ms");

//            TwoCaptcha solver = new TwoCaptcha("");
//            Normal captcha = new Normal(picPath);
//            captcha.setMinLen(4);
//            captcha.setMaxLen(4);
//            captcha.setCaseSensitive(true);
//            captcha.setLang("en");
//            long currentTime = System.currentTimeMillis();
//            try {
//                solver.solve(captcha);
//                System.out.println("Captcha solved: " + captcha.getCode()+" spend time: "+(System.currentTimeMillis()-currentTime)+"ms");
//                driver.findElement(By.id("CHK")).sendKeys(captcha.getCode());
//            } catch (Exception e) {
//                System.out.println("Error occurred: " + e.getMessage());
//            }
//        });
//        captchaThread.start();

        // 關閉座位圖，點全票然後選位置
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
            account.sendKeys("");
            pwd.sendKeys("");
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
