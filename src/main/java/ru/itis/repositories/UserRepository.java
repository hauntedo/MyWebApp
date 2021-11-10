package ru.itis.repositories;

import ru.itis.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User> {

    Optional<User> findFirstByEmailAndPassword(String email, String pass);
    Optional<User> findByEmail(String email);
    List<User> findAllEmployeeByEmployerId(Long id);
    String findToken(User u);
    Optional<User> findByToken(String token);
    void save(User u, UUID uuid);

}
