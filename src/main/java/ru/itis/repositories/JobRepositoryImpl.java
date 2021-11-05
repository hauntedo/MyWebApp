package ru.itis.repositories;

import ru.itis.models.Job;
import ru.itis.models.TaskState;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JobRepositoryImpl implements JobRepository {

    //language=SQL
    private static final String SQL_INSERT_JOB = "insert into Jobs(title, description, employer_id, active) values " +
            "(?,?,?,?)";

    //language=SQL
    private static final String SQL_UPDATE_JOB = "update Jobs set title = ? and description = ? and employer_id = ? " +
            "and active = ? where id = ?";

    //language=SQL
    private static final String SQL_DELETE_JOB = "delete from Jobs where id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from Jobs";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from Jobs where id = ?";

    private SimpleJdbcTemplate template;
    private DataSource dataSource;

    public JobRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    private RowMapper<Job> jobRowMapper = row -> Job.builder()
            .id(row.getLong("id"))
            .title(row.getString("title"))
            .description(row.getString("description"))
            .employerId(row.getLong("employer_id"))
            .active(row.getBoolean("active"))
            .build();


    @Override
    public void save(Job entity) {
        template.update(SQL_INSERT_JOB, entity.getTitle(), entity.getDescription(),
                entity.getEmployerId(), true);
    }

    @Override
    public void update(Job entity) {
        template.update(SQL_UPDATE_JOB, entity.getTitle(), entity.getDescription(),
                entity.getEmployerId(), true, entity.getId());
    }

    @Override
    public void delete(Job entity) {
        template.update(SQL_DELETE_JOB, entity.getId());
    }

    @Override
    public List<Job> findAll() {
        List<Job> jobs = template.query(SQL_FIND_ALL, jobRowMapper);
        return jobs.isEmpty()?null:jobs;
    }

    @Override
    public Optional<Job> findById(Long id) {
        List<Job> jobs = template.query(SQL_FIND_BY_ID, jobRowMapper, id);
        return jobs.isEmpty()?Optional.empty():Optional.of(jobs.get(0));пше
    }
}
