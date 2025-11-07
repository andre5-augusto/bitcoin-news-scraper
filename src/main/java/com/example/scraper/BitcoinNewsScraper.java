package com.example.scraper;

import com.example.model.NewsHeadline;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BitcoinNewsScraper {

    public List<NewsHeadline> scrapeCoinDesk() {
        List<NewsHeadline> headlines = new ArrayList<>();
        try {
            String seleniumUrl = System.getenv().getOrDefault("SELENIUM_REMOTE_URL", "http://localhost:4444/wd/hub");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            WebDriver driver = new RemoteWebDriver(new URL(seleniumUrl), options);

            driver.get("https://www.coindesk.com/search/?q=bitcoin");
            Thread.sleep(3000); // aguarda carregamento da página

            List<WebElement> newsItems = driver.findElements(By.cssSelector(".card-title")); // Manchetes
            List<WebElement> summaries = driver.findElements(By.cssSelector(".card-description"));
            List<WebElement> times = driver.findElements(By.cssSelector("time")); // Data/hora ISO

            int count = Math.min(newsItems.size(), Math.min(summaries.size(), times.size()));
            for (int i = 0; i < count; ++i) {
                String title = newsItems.get(i).getText();
                String summary = summaries.get(i).getText();
                String datetimeStr = times.get(i).getAttribute("datetime");
                LocalDateTime published = LocalDateTime.parse(datetimeStr.substring(0, 19)); // ISO em UTC ou local

                headlines.add(new NewsHeadline(published, title, summary));
            }
            driver.quit();
        } catch (Exception e) {
            System.err.println("Erro ao fazer scraping: " + e.getMessage());
        }
        return headlines;
    }
}