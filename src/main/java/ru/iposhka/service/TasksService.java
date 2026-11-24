package ru.iposhka.service;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import ru.iposhka.dto.UpdateTaskRequest;
import ru.iposhka.model.Task;
import ru.iposhka.repository.TasksRepository;

@ApplicationScoped
@RequiredArgsConstructor
public class TasksService {

    private final TasksRepository tasksRepository;

    public List<Task> getTasks() {
        return tasksRepository.findAll();
    }

    public Task createTask(Task task) {
        return tasksRepository.insertTask(task);
    }

    public Task update(UUID id, UpdateTaskRequest req) {
        return tasksRepository.update(id, req.name(), req.description()).orElse(null);
    }
}
