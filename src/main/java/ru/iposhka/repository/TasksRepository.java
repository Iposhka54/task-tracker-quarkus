package ru.iposhka.repository;

import static ru.iposhka.jooq.Tables.TASKS;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import ru.iposhka.jooq.tables.records.TasksRecord;
import ru.iposhka.model.Task;

@ApplicationScoped
@RequiredArgsConstructor
public class TasksRepository {

    private final DSLContext dsl;

    public List<Task> findAll() {
        return dsl.selectFrom(TASKS)
                .where(TASKS.DELETED_AT.isNull())
                .fetch(TasksRepository::toTask);
    }

    public Task insertTask(Task task) {
        var record = dsl.insertInto(TASKS)
                .columns(TASKS.NAME, TASKS.DESCRIPTION)
                .values(task.getName(), task.getDescription())
                .returning(TASKS.ID)
                .fetchOne();
        task.setId(record.getId());
        return task;
    }

    public Optional<Task> update(UUID id, String name, String description) {
        return dsl.update(TASKS)
                .set(TASKS.NAME, name)
                .set(TASKS.DESCRIPTION, description)
                .set(TASKS.UPDATED_AT, LocalDateTime.now())
                .where(TASKS.ID.eq(id).and(TASKS.DELETED_AT.isNull()))
                .returning()
                .fetchOptional()
                .map(TasksRepository::toTask);
    }

    private static Task toTask(TasksRecord r) {
        return new Task(r.getId(), r.getName(), r.getDescription(),
                r.getCreatedAt(), r.getUpdatedAt(), r.getDeletedAt());
    }
}
