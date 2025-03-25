package at.spengergasse.library.dto;

import at.spengergasse.library.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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

    public BookDTO(Book entity) {
        super(entity);
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.pages = entity.getPages();
    }

    @Override
    Book toEntity() {
        return Book.builder()
                .title(title)
                .description(description)
                .pages(pages)
                .build();
    }
}
