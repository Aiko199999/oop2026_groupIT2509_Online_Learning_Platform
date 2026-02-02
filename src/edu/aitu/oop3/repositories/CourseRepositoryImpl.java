package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.IDB;
import edu.aitu.oop3.entities.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements ICourseRepository {
    private final IDB db;
    public CourseRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT id, title, is_archived FROM courses";

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Course course = new Course.Builder()
                        .setId(rs.getInt("id"))
                        .setTitle(rs.getString("title"))
                        .setIsArchived(rs.getBoolean("is_archived"))
                        .build();
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return courses;
    }

    @Override
    public Course getById(int id) {
        String sql = "SELECT id, title, is_archived FROM courses WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Course.Builder()
                        .setId(rs.getInt("id"))
                        .setTitle(rs.getString("title"))
                        .setIsArchived(rs.getBoolean("is_archived"))
                        .build();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}