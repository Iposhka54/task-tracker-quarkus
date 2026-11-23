package ru.iposhka.service;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.iposhka.model.Task;
import ru.iposhka.repository.TasksRepository;

@ApplicationScoped
@RequiredArgsConstructor
public class TasksService {

    private final TasksRepository tasksRepository;

    public List<Task> getTasks() {
        return tasksRepository.findAll();
    }
}
