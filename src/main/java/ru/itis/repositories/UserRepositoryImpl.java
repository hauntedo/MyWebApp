package ru.itis.repositories;

import ru.itis.models.Task;
import ru.itis.models.TaskState;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from Users";

    //language=SQL
    private static final String SQL_INSERT_USER = "insert into Users(firstName, lastName, email, password, token)" +
            "values (?,?,?,?,?)";

    //language=SQL
    private static final String SQL_DELETE_USER = "delete from Users where id=?";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from Users where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_EMAIL_AND_PASS = "select * from Users where email = ?, password = ?";

    //language=SQL
    private static final String SQL_UPDATE_USER = "update Users set firstName = ?, lastName = ?, password = ?" +
            " where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_EMAIL = "select * from users where email = ?";

    //language=SQL
    private static final String SQL_FIND_TOKEN = "select * from Users where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_TOKEN = "select * from users where token = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_EMPLOYEE_BY_ID = "select * from Users inner join Relation R on Users.id = " +
            "R.employee_id where employer_id = ?";

    private DataSource dataSource;
    private SimpleJdbcTemplate template;

    public UserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("firstName"))
            .lastName(row.getString("lastName"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .token(row.getString("token"))
            .build();

    @Override
    public void save(User entity) {
        UUID uuid = UUID.randomUUID();
            template.update(SQL_INSERT_USER, entity.getFirstName(), entity.getLastName(),
                    entity.getEmail(), entity.getPassword(), uuid.toString());
    }

    @Override
    public void update(User entity) {
        template.update(SQL_UPDATE_USER, entity.getFirstName(), entity.getLastName(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(User entity) {
        template.update(SQL_DELETE_USER, entity.getId());
    }

    @Override
    public List<User> findAll() {
        List<User> users = template.query(SQL_FIND_ALL, userRowMapper);
        return users.isEmpty()?null:users;
    }

    @Override
    public Optional<User> findById(Long id) {
        List<User> users = template.query(SQL_FIND_BY_ID, userRowMapper, id);
        return users.isEmpty()?Optional.empty():Optional.of(users.get(0));
    }

    @Override
    public Optional<User> findFirstByEmailAndPassword(String email, String pass) {
        List<User> users = template.query(SQL_FIND_BY_EMAIL_AND_PASS, userRowMapper, email, pass);
        return users.isEmpty()?Optional.empty():Optional.of(users.get(0));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> users = template.query(SQL_FIND_BY_EMAIL, userRowMapper, email);
        return users.isEmpty()?Optional.empty():Optional.of(users.get(0));
    }

    @Override
    public List<User> findAllEmployeeByEmployerId(Long id) {
        List<User> users = template.query(SQL_FIND_ALL_EMPLOYEE_BY_ID, userRowMapper, id);
        return users.isEmpty()?null:users;
    }

    @Override
    public String findToken(User u) {
        List<User> users = template.query(SQL_FIND_TOKEN, userRowMapper, u.getId());
        return users.isEmpty()?null:users.get(0).getToken();
    }

    @Override
    public Optional<User> findByToken(String token) {
        List<User> users = template.query(SQL_FIND_BY_TOKEN, userRowMapper, token);
        return users.isEmpty()?Optional.empty():Optional.of(users.get(0));
    }

    @Override
    public void save(User u, UUID uuid) {
        template.update(SQL_INSERT_USER, u.getFirstName(), u.getLastName(),
                u.getEmail(), u.getPassword(), uuid.toString());
    }


}
