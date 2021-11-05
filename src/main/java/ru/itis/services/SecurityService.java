package ru.itis.services;

import ru.itis.models.User;

public interface SecurityService {

    void signUp(User u);
    void signIn(String email, String password);
    boolean isAuth();
    void validateEmail(String email);
}
