package ru.iposhka.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import ru.iposhka.model.Task;
import ru.iposhka.service.TasksService;

@Path("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TasksService tasksService;
    @Inject
    @ConfigProperty(name = "quarkus.rest.path", defaultValue = "/api/v1")
    String restPrefix;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTasks() {
        return Response.ok()
                .entity(tasksService.getTasks())
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTask(Task task) {
        task = tasksService.createTask(task);
        return Response.status(Status.CREATED)
                .location(URI.create(restPrefix + "/tasks/%s".formatted(task.getId().toString())))
                .entity(task)
                .build();
    }
}
