package at.spengergasse.library.dto;

import at.spengergasse.library.model.Person;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import java.util.UUID;

public record PersonDTO(
    Long id,
    @NotNull UUID uuid,
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotNull @Past LocalDate birthDate,
    @NotNull Person.Gender gender,
    @NotBlank @Email String email,
    @NotBlank String phone
) {}
