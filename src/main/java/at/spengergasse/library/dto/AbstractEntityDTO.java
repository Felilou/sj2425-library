package at.spengergasse.library.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AbstractEntityDTO(
    Long id,
    @NotNull UUID uuid
) {}
