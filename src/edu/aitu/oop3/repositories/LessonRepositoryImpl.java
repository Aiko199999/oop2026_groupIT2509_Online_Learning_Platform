package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.IDB;
import edu.aitu.oop3.entities.Lesson;

import java.sql.*;

public class LessonRepositoryImpl implements ILessonRepository {
    private final IDB db;

    public LessonRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public Lesson getById(int id) {
        String sql = "SELECT id, course_id, title FROM lessons WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return edu.aitu.oop3.factories.LessonFactory.createLesson(
                        "TEXT",
                        rs.getInt("id"),
                        rs.getInt("course_id"),
                        rs.getString("title")
                );
            }
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean lessonExists(int lessonId) {
        String sql = "SELECT 1 FROM lessons WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, lessonId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }
}