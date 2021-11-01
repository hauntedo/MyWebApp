package ru.itis.repositories;

import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from Users";

    //language=SQL
    private static final String SQL_INSERT_USER = "insert into Users(firstName, lastName, age, sex, email, password)" +
            "values (?,?,?,?,?,?,?)";

    //language=SQL
    private static final String SQL_DELETE_USER = "delete from Users where id=?";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from Users where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_EMAIL_AND_PASS = "select * from Users where email = ? and password = ?";

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
            .age(row.getInt("age"))
            .sex(row.getBoolean("sex"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .build();

    @Override
    public void save(User entity) {
        template.update(SQL_INSERT_USER, entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getSex(),
                entity.getEmail(), entity.getPassword());
    }

    @Override
    public void update(User entity) {

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
}
