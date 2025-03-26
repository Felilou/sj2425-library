package at.spengergasse.library.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record BookDTO(
    Long id,
    @NotNull UUID uuid,
    @NotBlank String title,
    String description,
    @Min(1) int pages,
    @NotNull UUID authorUuid,
    UUID locationUuid
) {}
