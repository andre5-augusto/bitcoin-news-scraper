package com.example.scraper;

import com.example.model.NewsHeadline;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BitcoinNewsScraperTest {

    @Test
    public void testScrapeCoinDesk() {
        BitcoinNewsScraper scraper = new BitcoinNewsScraper();
        List<NewsHeadline> headlines = scraper.scrapeCoinDesk();

        assertTrue(headlines.size() > 0, "Should extract at least one headline.");
        assertTrue(headlines.get(0).getTitle() != null && !headlines.get(0).getTitle().isBlank());
        assertTrue(headlines.get(0).getSummary() != null && !headlines.get(0).getSummary().isBlank());
        assertTrue(headlines.get(0).getDateTime() != null);
    }
}