package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.IDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProgressRepositoryImpl implements IProgressRepository {
    private final IDB db;

    public ProgressRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public void markAsCompleted(int userId, int lessonId) {
        String sql = "INSERT INTO progress (user_id, lesson_id, is_completed) VALUES (?, ?, true)";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, lessonId);
            stmt.executeUpdate();

            System.out.println("Progress recorded for User ID " + userId + " and Lesson ID " + lessonId);
        } catch (SQLException e) {
            System.out.println("Progress already exists or DB error: " + e.getMessage());
        }
    }

    @Override
    public double getProgress(int userId, int courseId) {
        return 0;
    }
}