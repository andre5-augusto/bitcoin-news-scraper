package com.example;

import com.example.model.NewsHeadline;
import com.example.repository.NewsRepository;
import com.example.scraper.BitcoinNewsScraper;

import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        BitcoinNewsScraper scraper = new BitcoinNewsScraper();
        NewsRepository repo = new NewsRepository();

        List<NewsHeadline> headlines = scraper.scrapeCoinDesk();

        for (NewsHeadline hl : headlines) {
            repo.save(hl);
        }

        List<NewsHeadline> saved = repo.getAll();
        saved.forEach(h -> System.out.println(h.getDateTime() + " | " + h.getTitle() + " | " + h.getSummary()));
    }
}