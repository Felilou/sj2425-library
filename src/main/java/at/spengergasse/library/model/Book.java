package at.spengergasse.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
public class Book extends AbstractEntity {

    String title;
    String description;
    int pages;

    @ManyToOne
    @JsonBackReference
    private Author author;

    @ManyToOne
    @JsonBackReference
    private Location location;

    @Override
    protected boolean individualEquals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        Book book = (Book) other;
        return getPages() == book.getPages() && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getDescription(), book.getDescription()) && Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getLocation(), book.getLocation());
    }

    @Override
    protected int individualHashCode() {
        return Objects.hash(super.hashCode(), getTitle(), getDescription(), getPages(), getAuthor(), getLocation());
    }

    public void setAuthor(Author author) {
        this.author = author;
        if(!author.containsBook(this))
            author.addBook(this);
    }

    public void removeAuthor() {
        Author author = this.author;
        this.author = null;
        if(author.containsBook(this)) {
            author.removeBook(this);
        };
    }

    public void setLocation(@NotNull Location location) {
        this.location = location;
        if(!location.containsBook(this)) {
            location.addBook(this);
        }
    }

    public void removeLocation() {
        if(location != null && location.containsBook(this)) {
            location.removeBook(this);
        }
        this.location = null;
    }

    @Override
    public void afterRemoveUUID() {
        author.removeBook(this);
    }
}
