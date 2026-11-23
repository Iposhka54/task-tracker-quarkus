package ru.iposhka.repository;

import static ru.iposhka.jooq.Tables.TASKS;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import ru.iposhka.model.Task;

@ApplicationScoped
@RequiredArgsConstructor
public class TasksRepository {

    private final DSLContext dsl;

    public List<Task> findAll() {
        return dsl.selectFrom(TASKS)
                .where(TASKS.DELETED_AT.isNull())
                .fetch(r -> new Task(r.getId(), r.getName(), r.getDescription(),
                        r.getCreatedAt(), r.getUpdatedAt(), r.getDeletedAt()));
    }
}
