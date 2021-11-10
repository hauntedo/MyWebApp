package ru.itis.services;

import ru.itis.models.Task;
import ru.itis.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    void createTask(Task t);
    void updateTask(Task t);
    void deleteTask(Task t);
    Optional<Task> getTaskById(Long id);
    List<Task> getAllTaskByEmployerId(Long id);

}
