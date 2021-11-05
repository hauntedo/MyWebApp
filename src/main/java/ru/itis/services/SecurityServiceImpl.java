package ru.itis.services;

import ru.itis.exceptions.InvalidEmailException;
import ru.itis.exceptions.NoSuchLoginException;
import ru.itis.exceptions.WrongPasswordException;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

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
    public void signUp(User u) {
        validateEmail(u.getEmail());
        userRepository.save(u);
    }

    @Override
    public void signIn(String email, String password) {
        User u = userRepository.findByEmail(email).get();
        if (u == null) {
            throw new NoSuchLoginException("No email " + email);
        }
        if (!u.getPassword().equals(password)) {
            throw new WrongPasswordException("Неверный пароль");
        }
    }

    @Override
    public boolean isAuth() {
        return false;
    }

    @Override
    public void validateEmail(String email) {
        Matcher m = Pattern.compile(EMAIL_REGEX).matcher(email);
        if (!m.matches()) {
            throw new InvalidEmailException(email + " is invalid");
        }
    }
}
