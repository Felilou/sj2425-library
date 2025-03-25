package at.spengergasse.library.dto;

import at.spengergasse.library.model.Address;
import at.spengergasse.library.model.Author;
import at.spengergasse.library.model.Person;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final String email;

    private final String phone;

    @JsonManagedReference
    private final List<BookDTO> books;

    public static class simpleAuthorDTO extends AbstractDTO<Author> {

        @Override
        public Author toEntity() {
            return null;
        }
    }

    public AuthorDTO(Author author) {
        super(author);
        firstName = author.getFirstName();
        lastName = author.getLastName();
        birthDate = author.getBirthDate();
        gender = author.getGender();
        email = author.getEmail();
        phone = author.getPhone();

        books = author.getBooks().stream().map((b) -> {
            BookDTO bookDTO = new BookDTO(b);
            bookDTO.setAuthor(this);
            return bookDTO;
        }).collect(Collectors.toList());

    }

    @Override
    public Author toEntity() {
        return Author.builder()
                .firstName(getFirstName())
                .lastName(getLastName())
                .birthDate(getBirthDate())
                .gender(getGender())
                .build();
    }

}
