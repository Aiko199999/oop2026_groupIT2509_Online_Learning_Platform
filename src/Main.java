//import edu.aitu.oop3.db.DatabaseConnection;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//public class Main {
//    public static void main(String[] args) {
//        System.out.println("Connecting to Supabase...");
//        try (Connection connection = DatabaseConnection.getConnection()) {
//            System.out.println("Connected successfully!");
//            String sql = "SELECT CURRENT_TIMESTAMP";
//            try (PreparedStatement stmt = connection.prepareStatement(sql);
//                 ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    System.out.println("Database time: " + rs.getTimestamp(1));
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error while connecting to database:");
//            e.printStackTrace();
//        }
//    }
//}

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.db.IDB;
import edu.aitu.oop3.exceptions.CourseArchivedException;
import edu.aitu.oop3.repositories.*;
import edu.aitu.oop3.services.CourseService;
import edu.aitu.oop3.services.ProgressService;

public class Main {
    public static void main(String[] args) {
        IDB db = new DatabaseConnection();

        ICourseRepository courseRepo = new CourseRepositoryImpl(db);
        IEnrollmentRepository enrollRepo = new EnrollmentRepositoryImpl(db);
        CourseService courseService=new CourseService(courseRepo,enrollRepo);
        IProgressRepository progressRepo = new ProgressRepositoryImpl(db);
        ProgressService progressService=new ProgressService(progressRepo);
        IUserRepository userRepo = new UserRepositoryImpl(db);

        try {
            courseService.enrollUser(1, 1);
            progressService.markLessonAsCompleted(1,1);
        } catch (CourseArchivedException e) {
            System.out.println("Error:"+e.getMessage());
        }
    }
}