package com.example.repository;

import com.example.model.NewsHeadline;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

class NewsRepositoryTest {

    NewsRepository repo;

    @BeforeEach
    void setup() throws SQLException {
        repo = new NewsRepository();
    }

    @Test
    void testSaveAndRetrieve() throws SQLException {
        NewsHeadline headline = new NewsHeadline(LocalDateTime.now(), "Bitcoin hits ATH!", "Bitcoin reached a new all-time high...");
        repo.save(headline);

        List<NewsHeadline> headlines = repo.getAll();
        Assertions.assertFalse(headlines.isEmpty());
        Assertions.assertEquals("Bitcoin hits ATH!", headlines.get(0).getTitle());
    }
}