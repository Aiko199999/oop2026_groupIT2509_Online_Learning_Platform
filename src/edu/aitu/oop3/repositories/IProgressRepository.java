package edu.aitu.oop3.repositories;

public interface IProgressRepository {
    void markAsCompleted(int userId,int lessonId);
    double getProgress(int userId, int courseId);
}
