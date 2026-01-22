package edu.aitu.oop3.repositories;

import edu.aitu.oop3.entities.Cours e;
import java.util.List;

public interface ICourseRepository {
    List<Course> getAllCourses();
    Course getCourseById(int id);
}