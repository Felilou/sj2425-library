package at.spengergasse.library.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDTOTest {

    private Author author;
    private Book book;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setFirstName("Stephen");
        author.setLastName("King");
        author.setBirthDate(LocalDate.of(1947, 9, 21));
        author.setGender(Person.Gender.MALE);

        book = new Book();
        book.title = "The Shining";
        book.description = "Horror novel";
        book.pages = 447;
    }

    @Test
    void testAddBook() {
        // When
        author.addBook(book);

        // Then
        assertTrue(author.getBooks().contains(book));
        assertEquals(author, book.getAuthor());
    }

    @Test
    void testRemoveBook() {
        // Given
        author.addBook(book);
        assertTrue(author.getBooks().contains(book));

        // When
        author.removeBook(book);

        // Then
        assertFalse(author.getBooks().contains(book));
        assertNull(book.getAuthor());
    }

    @Test
    void testContainsBook() {
        // Given
        assertFalse(author.containsBook(book));

        // When
        author.addBook(book);

        // Then
        assertTrue(author.containsBook(book));
    }
}
