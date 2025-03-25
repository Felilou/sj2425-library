package at.spengergasse.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Location extends AbstractEntity {

    public enum Type {
        LIBRARY,
        WAREHOUSE,
        STORE,
    }

    @Enumerated(EnumType.STRING)
    private Type type;
    private Address address;

    @ManyToMany
    @JsonManagedReference
    @JsonBackReference
    private final List<Location> connectionsTo = new ArrayList<>();

    @ManyToMany
    @JsonManagedReference
    @JsonBackReference
    private final List<Location> connectionsFrom = new ArrayList<>();

    @OneToMany(mappedBy = "location")
    @JsonManagedReference
    private final List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "location")
    @JsonManagedReference
    private final List<Employee> employees = new ArrayList<>();

    @Override
    protected boolean individualEquals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        Location location = (Location) other;
        return getType() == location.getType() && Objects.equals(getAddress(), location.getAddress()) && Objects.equals(getConnectionsTo(), location.getConnectionsTo()) && Objects.equals(getBooks(), location.getBooks()) && Objects.equals(getEmployees(), location.getEmployees());
    }

    @Override
    protected int individualHashCode() {
        return Objects.hash(super.hashCode(), getType(), getAddress(), getConnectionsTo(), getBooks(), getEmployees());
    }

    // Methoden für Book-Beziehung
    public void addBook(Book book) {
        books.add(book);
        if (book.getLocation() == null || !book.getLocation().equals(this)) {
            book.setLocation(this);
        }
    }

    public void removeBook(Book book) {
        books.remove(book);
        if (book.getLocation().equals(this)) {
            book.removeLocation();
        }
    }

    public boolean containsBook(Book book) {
        return books.contains(book);
    }

    // Methoden für Employee-Beziehung
    public void addEmployee(Employee employee) {
        employees.add(employee);
        if (employee.getLocation() == null || !employee.getLocation().equals(this)) {
            employee.setLocation(this);
        }
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        if (employee.getLocation()!=null && employee.getLocation().equals(this)) {
            employee.removeLocation();
        }
    }

    public boolean containsEmployee(Employee employee) {
        return employees.contains(employee);
    }

    public void addConnectionTo(Location location) {
        connectionsTo.add(location);
        if (!location.getConnectionsFrom().contains(this)) {
            location.addConnectionFrom(this);
        }
    }

    private void addConnectionFrom(Location location) {
        connectionsFrom.add(location);
    }

    public void removeConnectionTo(Location location) {
        connectionsTo.remove(location);
        if (location.getConnectionsFrom().contains(this)) {
            location.removeConnectionFrom(this);
        }
    }

    private void removeConnectionFrom(Location location) {
        connectionsFrom.remove(location);
    }

    @Override
    public void afterRemoveUUID() {
        for(Employee employee : employees) {
            removeEmployee(employee);
        }
        for(Book book : books) {
            removeBook(book);
        }
        for(Location location : connectionsFrom) {
            location.removeConnectionTo(this);
        }
    }
}
