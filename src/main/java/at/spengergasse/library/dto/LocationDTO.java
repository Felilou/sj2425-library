package at.spengergasse.library.dto;

import at.spengergasse.library.model.Location;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public record LocationDTO(
    Long id,
    @NotNull UUID uuid,
    @NotNull Location.Type type,
    @NotNull @Valid AddressDTO address,
    List<UUID> connectionsToUuids,
    List<UUID> booksUuids,
    List<UUID> employeesUuids
) {}
