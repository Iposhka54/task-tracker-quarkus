package ru.iposhka.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import ru.iposhka.dto.UpdateTaskRequest;
import ru.iposhka.model.Task;
import ru.iposhka.service.TasksService;

@Path("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TasksService tasksService;

    @GET
    public Response getTasks() {
        return Response.ok()
                .entity(tasksService.getTasks())
                .build();
    }

    @POST
    public Response createTask(Task task) {
        task = tasksService.createTask(task);
        return Response.status(Status.CREATED)
                .location(URI.create("/tasks/%s".formatted(task.getId().toString())))
                .entity(task)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, @Valid UpdateTaskRequest req) {
        Task updated = tasksService.update(id, req);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated).build();
    }
}
