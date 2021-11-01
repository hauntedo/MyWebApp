package ru.itis.repositories;

import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User> {

    Optional<User> findFirstByEmailAndPassword(String email, String pass);
}
