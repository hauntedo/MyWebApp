package ru.itis.services;

import ru.itis.models.Task;
import ru.itis.repositories.TaskRepository;

import java.util.Optional;

public class TaskServiceImpl implements TaskService{

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void createTask(Task t) {
        taskRepository.save(t);
    }

    @Override
    public void updateTask(Task t) {
        taskRepository.update(t);
    }

    @Override
    public void deleteTask(Task t) {
        taskRepository.delete(t);
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
}
