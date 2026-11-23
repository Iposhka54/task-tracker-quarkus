package ru.iposhka.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import ru.iposhka.service.TasksService;

@Path("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TasksService tasksService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTasks() {
        return Response.ok()
                .entity(tasksService.getTasks())
                .build();
    }
}
