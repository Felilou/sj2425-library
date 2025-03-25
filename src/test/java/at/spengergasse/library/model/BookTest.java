package at.spengergasse.library.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;
    private Author author;
    private Location location;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.title = "1984";
        book.description = "Dystopian novel";
        book.pages = 328;

        author = new Author();
        author.setFirstName("George");
        author.setLastName("Orwell");
        author.setBirthDate(LocalDate.of(1903, 6, 25));
        author.setGender(Person.Gender.MALE);

        location = new Location();
        location.setType(Location.Type.LIBRARY);
        Address address = new Address();
        address.setCity("Vienna");
        address.setStreet("Spengergasse");
        address.setHouseNumber(20);
        address.setPostalCode(1050);
        address.setCountry("Austria");
        location.setAddress(address);
    }

    @Test
    void testSetAuthor() {
        // When
        book.setAuthor(author);

        // Then
        assertEquals(author, book.getAuthor());
        assertTrue(author.getBooks().contains(book));
    }

    @Test
    void testRemoveAuthor() {
        // Given
        book.setAuthor(author);
        assertEquals(author, book.getAuthor());
        assertTrue(author.getBooks().contains(book));

        // When
        book.removeAuthor();

        // Then
        assertNull(book.getAuthor());
        assertFalse(author.getBooks().contains(book));
    }

    @Test
    void testSetLocation() {
        // When
        book.setLocation(location);

        // Then
        assertEquals(location, book.getLocation());
        assertTrue(location.getBooks().contains(book));
    }

    @Test
    void testRemoveLocation() {
        // Given
        book.setLocation(location);
        assertEquals(location, book.getLocation());

        // When
        book.removeLocation();

        // Then
        assertNull(book.getLocation());
        assertFalse(location.getBooks().contains(book));
    }
}
