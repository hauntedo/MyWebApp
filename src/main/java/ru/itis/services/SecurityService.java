package ru.itis.services;

import ru.itis.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface SecurityService {

    void signUp(User u, HttpSession httpSession);
    void signIn(String email, String password, HttpSession session);
    boolean isAuth(HttpServletRequest request, HttpSession session);
    void validateEmail(String email);
    void signOut(HttpServletRequest request, HttpServletResponse response);
}
