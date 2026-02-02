package edu.aitu.oop3.entities;

import  java.util.ArrayList;
import  java.util.List;

public class Course {
    private final int id;
    private final String title;
    private final String description;
    private final boolean isArchived;
    private final List<Lesson> lessons;
    private final List<String> tags;

    public Course(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.isArchived = builder.isArchived;
        this.lessons = builder.lessons;
        this.tags = builder.tags;
    }

    public static class Builder {
        private int id;
        private String title;
        private String description;
        private boolean isArchived;
        private List<Lesson> lessons = new ArrayList<>();
        private List<String> tags = new ArrayList<>();

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setIsArchived(boolean isArchived) {
            this.isArchived = isArchived;
            return this;
        }

        public Builder setLessons(List<Lesson> lessons) {
            this.lessons = lessons;
            return this;
        }

        public Builder setTags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public List<Lesson> getLessons() {
        return lessons; }

    public List<String> getTags() {
        return tags;
    }
}