package at.spengergasse.library.dto;

import at.spengergasse.library.model.Book;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BookDTO extends AbstractDTO<Book> {

    @Length(min = 1, max = 100)
    @NotBlank
    @NotNull
    private final String title;

    @NotBlank
    @NotNull
    @Length(min = 1, max = 255)
    private final String description;

    @Positive
    private final int pages;

    @JsonBackReference
    @NotNull
    private AuthorDTO author;

    @JsonBackReference
    @NotNull
    private LocationDTO location;

    public BookDTO(Book entity) {
        super(entity);

        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.pages = entity.getPages();
    }

    public BookDTO(Book entity, @Valid AuthorDTO author, @Valid LocationDTO location) {
        this(entity);
        setAuthor(author);
        setLocation(location);
    }

    public void setAuthor(AuthorDTO author) {
        if(this.author != null) {
            throw new IllegalStateException("Author already set");
        }
        this.author = author;
    }

    public void setLocation(LocationDTO location) {
        if(this.location != null) {
            throw new IllegalStateException("Location already set");
        }
        this.location = location;
    }

    @Override
    public Book toEntity() {
        return Book.builder()
                .title(title)
                .description(description)
                .pages(pages)
                .build();
    }
}
