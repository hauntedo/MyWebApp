package ru.itis.repositories;

import ru.itis.models.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task> {

    List<Task> findAllTaskByEmployeeId(Long id);
}
