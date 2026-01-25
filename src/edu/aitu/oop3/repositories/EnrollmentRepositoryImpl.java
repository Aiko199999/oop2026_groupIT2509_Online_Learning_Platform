package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.IDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentRepositoryImpl implements IEnrollmentRepository{
    private final IDB db;

    public EnrollmentRepositoryImpl(IDB db){
        this.db=db;
    }

    @Override
    public void enroll(int userId, int courseId) {
        String sql="insert into enrollments(user_id,course_id) values (?,?)";
        try(Connection conn= db.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1,userId);
            stmt.setInt(2,courseId);
            stmt.executeUpdate();
            System.out.println("Successful enrollment in course!");
        }catch(SQLException e){
            System.out.println("Failure! "+e.getMessage());
        }
    }

    @Override
    public boolean isUserEnrolled(int userId, int courseId) {
        String sql = "SELECT 1 FROM enrollments WHERE user_id = ? AND course_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, courseId);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Error checking enrollment: " + e.getMessage());
            return false;
        }
    }
}
