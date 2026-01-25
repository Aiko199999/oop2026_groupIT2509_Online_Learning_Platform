package edu.aitu.oop3.repositories;

import edu.aitu.oop3.entities.User;

public interface IUserRepository {
    User getById(int id);
}
