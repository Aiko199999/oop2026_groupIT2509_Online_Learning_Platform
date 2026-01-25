package edu.aitu.oop3.repositories;

import edu.aitu.oop3.entities.Lesson;

public interface ILessonRepository {
    Lesson getById(int id);
    boolean lessonExists(int lessonId);
}
