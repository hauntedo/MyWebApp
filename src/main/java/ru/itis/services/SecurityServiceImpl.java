package ru.itis.services;

import ru.itis.exceptions.InvalidEmailException;
import ru.itis.exceptions.NoSuchLoginException;
import ru.itis.exceptions.OccupiedEmailException;
import ru.itis.exceptions.WrongPasswordException;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurityServiceImpl implements SecurityService {

    public static final String EMAIL_REGEX = "^(.+)@(.+)$";

    private UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signUp(User u, HttpSession session) {
        validateEmail(u.getEmail());
        userRepository.save(u);
        session.setAttribute("user", u);
    }

    @Override
    public void signIn(String email, String password, HttpSession session) {
        if (userRepository.findByEmail(email).isPresent()) {
            User u = userRepository.findByEmail(email).get();
            if (u == null) {
                throw new NoSuchLoginException("No email " + email);
            }
            if (!u.getPassword().equals(password)) {
                throw new WrongPasswordException("Неверный пароль");
            }
            session.setAttribute("user", u);
        }
    }

    @Override
    public boolean isAuth(HttpServletRequest request, HttpSession session) {
        if (session.getAttribute("user")!=null) {
            return true;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("user")) {
                    Optional<User> u = userRepository.findById(Long.valueOf(c.getValue()));
                    if (u.isPresent()) {
                        session.setAttribute("user", u);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void validateEmail(String email) {
        Matcher m = Pattern.compile(EMAIL_REGEX).matcher(email);
        if (!m.matches()) {
            throw new InvalidEmailException(email + " is invalid");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new OccupiedEmailException(email + " is occupied");
        }
    }

    @Override
    public void signOut() {

    }
}
