package edu.aitu.oop3.entities;

public class VideoLesson extends Lesson {
    public VideoLesson(int id, int courseId, String title) {
        super(id, courseId, title);
    }

    @Override
    public String getLessonType() {
        return "VIDEO_PLAYER";
    }
}