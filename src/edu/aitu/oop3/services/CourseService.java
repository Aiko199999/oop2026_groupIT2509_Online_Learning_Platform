package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Course;
import edu.aitu.oop3.exceptions.CourseArchivedException;
import edu.aitu.oop3.repositories.ICourseRepository;
import edu.aitu.oop3.repositories.IEnrollmentRepository;

public class CourseService {
    private final ICourseRepository courseRepo;
    private final IEnrollmentRepository enrollmentRepo;

    public CourseService(ICourseRepository courseRepo, IEnrollmentRepository enrollmentRepo) {
        this.courseRepo = courseRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    public void enrollUser(int userId, int courseId) throws CourseArchivedException {
        Course course = courseRepo.getCourseById(courseId);

        if (course == null) {
            System.out.println("Course is not found.");
            return;
        }

        // ПРОВЕРКА: Если курс в архиве — кидаем исключение (как в задании!)
        if (course.isArchived()) {
            throw new CourseArchivedException("You can't enroll in '" + course.getTitle() + "', because it's archived");
        }

        enrollmentRepo.enroll(userId, courseId);
    }
}