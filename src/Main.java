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

import edu.aitu.oop3.config.PlatformConfig;
import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.db.IDB;
import edu.aitu.oop3.entities.Course;
import edu.aitu.oop3.entities.Lesson;
import edu.aitu.oop3.exceptions.CourseArchivedException;
import edu.aitu.oop3.exceptions.LessonNotFoundException;
import edu.aitu.oop3.exceptions.UserNotEnrolledException;
import edu.aitu.oop3.factories.LessonFactory;
import edu.aitu.oop3.repositories.*;
import edu.aitu.oop3.services.CourseService;
import edu.aitu.oop3.services.LessonService;
import edu.aitu.oop3.services.ProgressService;
import edu.aitu.oop3.util.Page;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IDB db = new DatabaseConnection();

        ICourseRepository courseRepo = new CourseRepositoryImpl(db);
        IEnrollmentRepository enrollRepo = new EnrollmentRepositoryImpl(db);
        IProgressRepository progressRepo = new ProgressRepositoryImpl(db);
        IUserRepository userRepo = new UserRepositoryImpl(db);
        ILessonRepository lessonRepo = new LessonRepositoryImpl(db);

        CourseService courseService=new CourseService(courseRepo,enrollRepo);
        ProgressService progressService=new ProgressService(progressRepo);
        LessonService lessonService=new LessonService(lessonRepo,enrollRepo);

        PlatformConfig config=PlatformConfig.getInstance();
        System.out.println("Welcome to: "+config.getPlatformName());

        Course myCourse=new Course.Builder()
                .setId(1)
                .setTitle("Java patterns")
                .setDescription("Milestone 2")
                .build();

        System.out.println("Created course: "+myCourse.getTitle());

        // Testing the Factory
        Lesson myVideo = LessonFactory.createLesson("VIDEO", 1, 10, "Intro to Java");
        Lesson myText = LessonFactory.createLesson("TEXT", 2, 10, "Read me first");
        Lesson myQuiz = LessonFactory.createLesson("QUIZ", 3, 10, "SOLID");

        System.out.println(myVideo.getTitle() + " uses: " + myVideo.getLessonType());
        System.out.println(myText.getTitle() + " uses: " + myText.getLessonType());
        System.out.println(myQuiz.getTitle() + " uses: " + myQuiz.getLessonType());

        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course.Builder().setId(1).setTitle("Java Basics").build());
        courseList.add(new Course.Builder().setId(2).setTitle("Advanced Python").build());
        courseList.add(new Course.Builder().setId(3).setTitle("Java Design Patterns").build());

        List<Course> filtered = courseList.stream()
                .filter(c -> c.getTitle().contains("Java"))
                .toList();

        Page<Course> coursePage = new Page<>(filtered);

        System.out.println("Search Results (Found " + coursePage.getTotalElements() + " items):");
        for (Course c : coursePage.getItems()) {
            System.out.println(" - " + c.getTitle());
        }

        String searchTag = "AITU";
        boolean hasTag = myCourse.getTags().stream()
                .anyMatch(tag -> tag.equalsIgnoreCase(searchTag));

        edu.aitu.oop3.util.Page<String> tagPage = new edu.aitu.oop3.util.Page<>(myCourse.getTags());

        System.out.println("Does course have '" + searchTag + "' tag? " + hasTag);
        System.out.println("Total tags found in Generic Page: " + tagPage.getTotalElements());

//        try {
//            courseService.enrollUser(1, 1);
//            progressService.markLessonAsCompleted(1,1);
//            lessonService.openLesson(1,1);
//            lessonService.openLesson(2,1);
//        } catch (CourseArchivedException | UserNotEnrolledException | LessonNotFoundException e) {
//            System.out.println("Error:"+e.getMessage());
//        }
    }
}