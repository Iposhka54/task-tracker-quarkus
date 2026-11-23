package ru.iposhka.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.iposhka.model.Task;
import ru.iposhka.service.TasksService;

@Path("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TasksService tasksService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getTasks() {
        return tasksService.getTasks();
    }
}
