package ru.iposhka.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.security.sasl.AuthorizeCallback;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
