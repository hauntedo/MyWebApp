package ru.itis.services;

import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(User u);
    void deleteUser(User u);
    void updateUser(User u);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmailAndPass(String email, String pass);
    List<User> getAllEmployeeByEmployerId(Long id);

}
