package at.spengergasse.library.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    private Location location;
    private Book book;
    private Employee employee;
    private Location otherLocation;

    @BeforeEach
    void setUp() {
        location = new Location();
        location.setType(Location.Type.LIBRARY);
        Address address = new Address();
        address.setCity("Vienna");
        address.setStreet("Spengergasse");
        address.setHouseNumber(20);
        address.setPostalCode(1050);
        address.setCountry("Austria");
        location.setAddress(address);

        otherLocation = new Location();
        otherLocation.setType(Location.Type.WAREHOUSE);
        Address otherAddress = new Address();
        otherAddress.setCity("Vienna");
        otherAddress.setStreet("Hauptstraße");
        otherAddress.setHouseNumber(10);
        otherAddress.setPostalCode(1010);
        otherAddress.setCountry("Austria");
        otherLocation.setAddress(otherAddress);

        book = new Book();
        book.title = "Clean Code";
        book.description = "Programming book";
        book.pages = 464;

        employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setBirthDate(LocalDate.of(1980, 1, 1));
        employee.setGender(Person.Gender.MALE);
        employee.setRole(Employee.Role.LIBRARIAN);
    }

    @Test
    void testAddBook() {
        // When
        location.addBook(book);

        // Then
        assertTrue(location.getBooks().contains(book));
        assertEquals(location, book.getLocation());
    }

    @Test
    void testRemoveBook() {
        // Given
        location.addBook(book);
        assertTrue(location.getBooks().contains(book));

        // When
        location.removeBook(book);

        // Then
        assertFalse(location.getBooks().contains(book));
        assertNull(book.getLocation());
    }

    @Test
    void testContainsBook() {
        // Given
        assertFalse(location.containsBook(book));

        // When
        location.addBook(book);

        // Then
        assertTrue(location.containsBook(book));
    }

    @Test
    void testAddEmployee() {
        // When
        location.addEmployee(employee);

        // Then
        assertTrue(location.getEmployees().contains(employee));
        assertEquals(location, employee.getLocation());
    }

    @Test
    void testRemoveEmployee() {
        // Given
        location.addEmployee(employee);
        assertTrue(location.getEmployees().contains(employee));

        // When
        location.removeEmployee(employee);

        // Then
        assertFalse(location.getEmployees().contains(employee));
        assertNull(employee.getLocation());
    }

    @Test
    void testContainsEmployee() {
        // Given
        assertFalse(location.containsEmployee(employee));

        // When
        location.addEmployee(employee);

        // Then
        assertTrue(location.containsEmployee(employee));
    }

    @Test
    void testLocationTypeEnum() {
        // Given
        location.setType(Location.Type.LIBRARY);

        // Then
        assertEquals(Location.Type.LIBRARY, location.getType());

        // When
        location.setType(Location.Type.STORE);

        // Then
        assertEquals(Location.Type.STORE, location.getType());
    }

    @Test
    void testAddressAssociation() {
        // Given
        Address newAddress = new Address();
        newAddress.setCity("Salzburg");
        newAddress.setStreet("Mozartplatz");
        newAddress.setHouseNumber(5);
        newAddress.setPostalCode(5020);
        newAddress.setCountry("Austria");

        // When
        location.setAddress(newAddress);

        // Then
        assertEquals(newAddress, location.getAddress());
        assertEquals("Salzburg", location.getAddress().getCity());
        assertEquals("Mozartplatz", location.getAddress().getStreet());
        assertEquals(5, location.getAddress().getHouseNumber());
        assertEquals(5020, location.getAddress().getPostalCode());
        assertEquals("Austria", location.getAddress().getCountry());
    }

    @Test
    void testConnectionsBetweenLocations() {
        // When
        location.getConnectionsTo().add(otherLocation);

        // Then
        assertTrue(location.getConnectionsTo().contains(otherLocation));
        assertEquals(1, location.getConnectionsTo().size());

        // When adding another connection
        Location thirdLocation = new Location();
        thirdLocation.setType(Location.Type.STORE);
        location.getConnectionsTo().add(thirdLocation);

        // Then
        assertEquals(2, location.getConnectionsTo().size());
        assertTrue(location.getConnectionsTo().contains(thirdLocation));
    }

    @Test
    void testBookRelocationBetweenLocations() {
        // Given
        location.addBook(book);
        assertTrue(location.getBooks().contains(book));
        assertEquals(location, book.getLocation());

        // When
        location.removeBook(book);
        otherLocation.addBook(book);

        // Then
        assertFalse(location.getBooks().contains(book));
        assertTrue(otherLocation.getBooks().contains(book));
        assertEquals(otherLocation, book.getLocation());
    }

    @Test
    void testEmployeeTransferBetweenLocations() {
        // Given
        location.addEmployee(employee);
        assertTrue(location.getEmployees().contains(employee));
        assertEquals(location, employee.getLocation());

        // When
        location.removeEmployee(employee);
        otherLocation.addEmployee(employee);

        // Then
        assertFalse(location.getEmployees().contains(employee));
        assertTrue(otherLocation.getEmployees().contains(employee));
        assertEquals(otherLocation, employee.getLocation());
    }

    @Test
    void testMultipleBooksInLocation() {
        // Given
        Book book1 = new Book();
        book1.title = "Clean Code";
        book1.description = "Programming book";
        book1.pages = 464;

        Book book2 = new Book();
        book2.title = "Design Patterns";
        book2.description = "Software engineering book";
        book2.pages = 395;

        // When
        location.addBook(book1);
        location.addBook(book2);

        // Then
        assertEquals(2, location.getBooks().size());
        assertTrue(location.getBooks().contains(book1));
        assertTrue(location.getBooks().contains(book2));
        assertEquals(location, book1.getLocation());
        assertEquals(location, book2.getLocation());
    }

    @Test
    void testMultipleEmployeesInLocation() {
        // Given
        Employee employee1 = new Employee();
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setRole(Employee.Role.LIBRARIAN);

        Employee employee2 = new Employee();
        employee2.setFirstName("Jane");
        employee2.setLastName("Smith");
        employee2.setRole(Employee.Role.MANAGER);

        // When
        location.addEmployee(employee1);
        location.addEmployee(employee2);

        // Then
        assertEquals(2, location.getEmployees().size());
        assertTrue(location.getEmployees().contains(employee1));
        assertTrue(location.getEmployees().contains(employee2));
        assertEquals(location, employee1.getLocation());
        assertEquals(location, employee2.getLocation());
    }
}
