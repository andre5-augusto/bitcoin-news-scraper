package com.example.repository;

import com.example.model.NewsHeadline;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsRepository {
    private final Connection connection;

    public NewsRepository() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb");
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS news_headlines (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "date_time TIMESTAMP, " +
                    "title VARCHAR(255), " +
                    "summary VARCHAR(1000))");
        }
    }

    public void save(NewsHeadline headline) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO news_headlines (date_time, title, summary) VALUES (?, ?, ?)")) {
            pstmt.setTimestamp(1, Timestamp.valueOf(headline.getDateTime()));
            pstmt.setString(2, headline.getTitle());
            pstmt.setString(3, headline.getSummary());
            pstmt.executeUpdate();
        }
    }

    public List<NewsHeadline> getAll() throws SQLException {
        List<NewsHeadline> headlines = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT date_time, title, summary FROM news_headlines")) {
            while (rs.next()) {
                headlines.add(new NewsHeadline(
                        rs.getTimestamp("date_time").toLocalDateTime(),
                        rs.getString("title"),
                        rs.getString("summary")));
            }
        }
        return headlines;
    }
}