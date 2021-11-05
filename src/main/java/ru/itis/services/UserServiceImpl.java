package ru.itis.services;

import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User u) {
        userRepository.save(u);
    }

    @Override
    public void deleteUser(User u) {
        userRepository.delete(u);
    }

    @Override
    public void updateUser(User u) {
        userRepository.update(u);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmailAndPass(String email, String pass) {
        return userRepository.findFirstByEmailAndPassword(email, pass);
    }

    @Override
    public List<User> getAllEmployeeByEmployerId(Long id) {
        return userRepository.findAllEmployeeByEmployerId(id);
    }
}
