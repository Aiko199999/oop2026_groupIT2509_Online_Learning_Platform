package edu.aitu.oop3.entities;

public class QuizLesson extends Lesson {
    public QuizLesson(int id, int courseId, String title) {
        super(id, courseId, title);
    }

    @Override
    public String getLessonType() {
        return "INTERACTIVE_QUIZ";
    }
}