package edu.aitu.oop3.repositories;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll();
    T getById(int id);
}