package edu.aitu.oop3.entities;

public class Course {
    private int id;
    private String title;
    private boolean isArchived;

    public Course(int id, String title, boolean isArchived) {
        this.id = id;
        this.title = title;
        this.isArchived = isArchived;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isArchived() {
        return isArchived;
    }

}