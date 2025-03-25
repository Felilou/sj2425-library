package at.spengergasse.library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@SuperBuilder
@Getter
public class Author extends Person {

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        if (book.getAuthor() == null || !book.getAuthor().equals(this))
            book.setAuthor(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        if(book.getAuthor() != null && book.getAuthor().equals(this))
            book.removeAuthor();
    }

    public boolean containsBook(Book book) {
        return books.contains(book);
    }

    @Override
    protected boolean individualEquals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        Author author = (Author) other;
        return Objects.equals(getBooks(), author.getBooks());
    }

    @Override
    protected int individualHashCode() {
        return Objects.hash(super.hashCode(), getBooks());
    }

    @Override
    public void afterRemoveUUID() {
        super.afterRemoveUUID();
        books.forEach(Book::removeAuthor);
    }
}
