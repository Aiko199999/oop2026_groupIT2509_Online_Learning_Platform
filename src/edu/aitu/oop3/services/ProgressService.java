package edu.aitu.oop3.services;

import edu.aitu.oop3.repositories.IProgressRepository;

public class ProgressService {
    private final IProgressRepository progressRepo;

    public ProgressService(IProgressRepository progressRepo) {
        this.progressRepo = progressRepo;
    }

    public void markLessonAsCompleted(int userId, int lessonId) {
        progressRepo.markAsCompleted(userId, lessonId);
    }

    public void viewCourseProgress(int userId, int courseId) {
        double percentage = progressRepo.getProgress(userId, courseId);
        System.out.println("Course progress: " + percentage + "%");
    }
}