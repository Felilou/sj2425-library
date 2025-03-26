package at.spengergasse.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddressDTO(
    @NotNull @Positive int postalCode,
    @NotNull @Positive int houseNumber,
    @NotBlank String city,
    @NotBlank String street,
    @NotBlank String country
) {}
