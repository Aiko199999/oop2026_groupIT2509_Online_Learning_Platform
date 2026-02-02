package edu.aitu.oop3.entities;

public class TextLesson extends Lesson {
    public TextLesson(int id, int courseId, String title) {
        super(id, courseId, title);
    }

    @Override
    public String getLessonType() {
        return "TEXT_READER";
    }
}