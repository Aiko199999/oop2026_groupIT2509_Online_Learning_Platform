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
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT id, title, is_archived FROM courses";

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getBoolean("is_archived")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return courses;
    }

    @Override
    public Course getCourseById(int id) {
        String sql = "SELECT id, title, is_archived FROM courses WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getBoolean("is_archived")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}