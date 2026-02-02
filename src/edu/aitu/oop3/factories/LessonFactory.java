package edu.aitu.oop3.factories;

import edu.aitu.oop3.entities.*;

public class LessonFactory {
    public static Lesson createLesson(String type, int id, int courseId, String title) {
        if (type == null) return null;

        return switch (type.toUpperCase()) {
            case "VIDEO" -> new VideoLesson(id, courseId, title);
            case "TEXT" -> new TextLesson(id, courseId, title);
            case "QUIZ" -> new QuizLesson(id, courseId, title);
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}