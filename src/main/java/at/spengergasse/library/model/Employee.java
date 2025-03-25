package at.spengergasse.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Employee extends Person {

    @Override
    protected boolean individualEquals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        Employee employee = (Employee) other;
        return getRole() == employee.getRole() && Objects.equals(getLocation(), employee.getLocation());
    }

    @Override
    protected int individualHashCode() {
        return Objects.hash(super.hashCode(), getRole(), getLocation());
    }

    public enum Role {
        LIBRARIAN,
        MANAGER,
        DIRECTOR,
    }

    @Setter
    private Role role;

    @ManyToOne
    private Location location;

    public void setLocation(@NotNull Location location) {
        if(this.location != null) {
            this.location.removeEmployee(this);
        }
        this.location = location;
        if(!location.containsEmployee(this)) {
            location.addEmployee(this);
        }
    }

    public void removeLocation() {
        Location location = getLocation();
        this.location = null;
        if(location.containsEmployee(this)) {
            location.removeEmployee(this);
        }
    }

    @Override
    public void afterRemoveUUID() {
        location.removeEmployee(this);
    }
}
