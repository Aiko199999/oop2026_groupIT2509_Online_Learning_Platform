package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.IDB;
import edu.aitu.oop3.entities.User;
import java.sql.*;

public class UserRepositoryImpl implements IUserRepository {
    private final IDB db;

    public UserRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public User getById(int id) {
        String sql = "SELECT id, name, email FROM users WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            System.out.println("User Search Error: " + e.getMessage());
        }
        return null;
    }
}