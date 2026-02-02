package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Course;
import edu.aitu.oop3.exceptions.CourseArchivedException;
import edu.aitu.oop3.repositories.ICourseRepository;
import edu.aitu.oop3.repositories.IEnrollmentRepository;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CourseService {
    private final ICourseRepository courseRepo;
    private final IEnrollmentRepository enrollmentRepo;

    public CourseService(ICourseRepository courseRepo, IEnrollmentRepository enrollmentRepo) {
        this.courseRepo = courseRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    public List<Course> searchCourses(Predicate<Course> filter) {
        List<Course> allCourses=courseRepo.getAll();
        return allCourses.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    public void enrollUser(int userId, int courseId) throws CourseArchivedException {
        Course course = courseRepo.getById(courseId);

        if (course == null) {
            System.out.println("Course is not found.");
            return;
        }

        if (course.isArchived()) {
            throw new CourseArchivedException("You can't enroll in '" + course.getTitle() + "'. It's archived");
        }

        enrollmentRepo.enroll(userId, courseId);
    }
}