package edu.aitu.oop3.repositories;

public interface IEnrollmentRepository {
    void enroll(int userId, int courseId);

    boolean isUserEnrolled(int userId, int courseId);
}
