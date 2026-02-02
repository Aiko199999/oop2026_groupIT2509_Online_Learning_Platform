package edu.aitu.oop3.entities;

public abstract class Lesson {
    private int id;
    private int courseId;
    private String title;

    public Lesson(int id, int courseId, String title) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
    }

    public abstract String getLessonType();

    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

}