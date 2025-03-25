package at.spengergasse.library.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee;
    private Location location;
    private Location newLocation;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setFirstName("Jane");
        employee.setLastName("Smith");
        employee.setBirthDate(LocalDate.of(1985, 5, 15));
        employee.setGender(Person.Gender.FEMALE);
        employee.setRole(Employee.Role.LIBRARIAN);

        location = new Location();
        location.setType(Location.Type.LIBRARY);
        Address address = new Address();
        address.setCity("Vienna");
        address.setStreet("Spengergasse");
        address.setHouseNumber(20);
        address.setPostalCode(1050);
        address.setCountry("Austria");
        location.setAddress(address);

        newLocation = new Location();
        newLocation.setType(Location.Type.STORE);
        Address newAddress = new Address();
        newAddress.setCity("Vienna");
        newAddress.setStreet("Hauptstraße");
        newAddress.setHouseNumber(1);
        newAddress.setPostalCode(1010);
        newAddress.setCountry("Austria");
        newLocation.setAddress(newAddress);
    }

    @Test
    void testSetLocation() {
        // When
        employee.setLocation(location);

        // Then
        assertEquals(location, employee.getLocation());
        assertTrue(location.getEmployees().contains(employee));
    }

    @Test
    void testRemoveLocation() {
        // Given
        employee.setLocation(location);
        assertEquals(location, employee.getLocation());

        // When
        employee.removeLocation();

        // Then
        assertNull(employee.getLocation());
        assertFalse(location.getEmployees().contains(employee));
    }

    @Test
    void testSetRole() {
        // When
        employee.setRole(Employee.Role.MANAGER);

        // Then
        assertEquals(Employee.Role.MANAGER, employee.getRole());
    }

    @Test
    void testChangeLocation() {
        // Given
        employee.setLocation(location);
        assertEquals(location, employee.getLocation());
        assertTrue(location.getEmployees().contains(employee));

        // When
        employee.setLocation(newLocation);

        // Then
        assertEquals(newLocation, employee.getLocation());
        assertTrue(newLocation.getEmployees().contains(employee));
        assertFalse(location.getEmployees().contains(employee));
    }

    @Test
    void testAllRoles() {
        // Test all roles in the enum
        employee.setRole(Employee.Role.LIBRARIAN);
        assertEquals(Employee.Role.LIBRARIAN, employee.getRole());

        employee.setRole(Employee.Role.MANAGER);
        assertEquals(Employee.Role.MANAGER, employee.getRole());

        employee.setRole(Employee.Role.DIRECTOR);
        assertEquals(Employee.Role.DIRECTOR, employee.getRole());
    }

    @Test
    void testInheritedPersonProperties() {
        // Given
        LocalDate birthDate = LocalDate.of(1990, 3, 15);

        // When
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setBirthDate(birthDate);
        employee.setGender(Person.Gender.MALE);

        // Then
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals(birthDate, employee.getBirthDate());
        assertEquals(Person.Gender.MALE, employee.getGender());
    }

}
