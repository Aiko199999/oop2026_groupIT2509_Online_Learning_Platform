package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Lesson;
import edu.aitu.oop3.exceptions.LessonNotFoundException;
import edu.aitu.oop3.exceptions.UserNotEnrolledException;
import edu.aitu.oop3.repositories.IEnrollmentRepository;
import edu.aitu.oop3.repositories.ILessonRepository;

public class LessonService {
    private final ILessonRepository lessonRepo;
    private final IEnrollmentRepository enrollmentRepo;

    public LessonService(ILessonRepository lessonRepo, IEnrollmentRepository enrollmentRepo) {
        this.lessonRepo = lessonRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    public void openLesson(int userId,int lessonId) throws LessonNotFoundException, UserNotEnrolledException {
        Lesson lesson=lessonRepo.getById(lessonId);
        if(lesson==null) throw new LessonNotFoundException("Lesson with this ID not found.");
        if(!enrollmentRepo.isUserEnrolled(userId, lesson.getCourseId())){
            throw new UserNotEnrolledException("You must enroll in the course.");
        }
        System.out.println("Opening: "+lesson.getTitle());
    }
}
