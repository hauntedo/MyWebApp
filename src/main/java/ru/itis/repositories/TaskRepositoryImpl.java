package ru.itis.repositories;

import ru.itis.models.Task;
import ru.itis.models.TaskState;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class TaskRepositoryImpl implements TaskRepository {

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from tasks where id = ?";

    //language=SQL
    private static final String SQL_INSERT_TASK = "insert into tasks(title, description, task_state," +
            "employer_id) values (?,?,?,?)";

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from Tasks";

    //language=SQL
    private static final String SQL_DELETE_TASK = "delete from Tasks where id=?";

    //language=SQL
    private static final String SQL_UPDATE_TASK = "update Tasks set title = ?, description = ?, task_state = ?, " +
            "employer_id = ? where id = ?";

    //language=SQL
    private static final String SQL_FIND_TASK_BY_EMPLOYER_ID = "select * from Tasks inner join Relation R on " +
            "Tasks.employer_id = R.employer_id where employee_id = ?;";

    private SimpleJdbcTemplate template;
    private DataSource dataSource;

    public TaskRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    private RowMapper<Task> taskRowMapper = row -> Task.builder()
            .id(row.getLong("id"))
            .title(row.getString("title"))
            .description(row.getString("description"))
            .taskState(TaskState.valueOf(row.getString("task_state")))
            .userId(row.getLong("employer_id"))
            .build();

    @Override
    public void save(Task entity) {
        template.update(SQL_INSERT_TASK, entity.getTitle(), entity.getDescription(), TaskState.OPEN.toString(), entity.getUserId());
    }

    @Override
    public void update(Task task) {
        template.update(SQL_UPDATE_TASK, task.getTitle(), task.getDescription(), task.getTaskState().toString(), task.getUserId()
                , task.getId());
    }

    @Override
    public void delete(Task entity) {
        template.update(SQL_DELETE_TASK, entity.getId());
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks = template.query(SQL_FIND_ALL, taskRowMapper);
        return tasks.isEmpty()?null:tasks;
    }

    @Override
    public Optional<Task> findById(Long id) {
        List<Task> tasks = template.query(SQL_FIND_BY_ID, taskRowMapper, id);
        return tasks.isEmpty()?Optional.empty():Optional.of(tasks.get(0));
    }


    @Override
    public List<Task> findAllTaskByEmployerId(Long id) {
        List<Task> tasks = template.query(SQL_FIND_TASK_BY_EMPLOYER_ID, taskRowMapper, id);
        return tasks.isEmpty()?null:tasks;
    }
}
