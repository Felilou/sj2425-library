package at.spengergasse.library.dto;

import at.spengergasse.library.model.Address;
import at.spengergasse.library.model.Author;
import at.spengergasse.library.model.Person;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class AuthorDTO extends AbstractDTO<Author> {

    @NotBlank
    @NotNull
    private final String firstName;

    @NotBlank
    @NotNull
    private final String lastName;

    @PastOrPresent
    private final LocalDate birthDate;

    private final Person.Gender gender;

    @Email
    private String email;

    private String phone;

    public AuthorDTO(Author author) {
        super(author);
        firstName = author.getFirstName();
        lastName = author.getLastName();
        birthDate = author.getBirthDate();
        gender = author.getGender();
    }

    @Override
    Author toEntity() {
        return Author.builder()
                .firstName(getFirstName())
                .lastName(getLastName())
                .birthDate(getBirthDate())
                .gender(getGender())
                .build();
    }

}
