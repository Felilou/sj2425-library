package at.spengergasse.library.dto;

import at.spengergasse.library.model.Employee;
import at.spengergasse.library.model.Person;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class EmployeeDTO extends AbstractDTO<Employee> {

    @NotBlank
    @NotNull
    private final String firstName;

    @NotBlank
    @NotNull
    private final String lastName;

    @PastOrPresent
    private final LocalDate birthDate;

    private final Person.Gender gender;

    @Email
    private final String email;

    private final String phone;

    private final Employee.Role role;

    @JsonBackReference
    private LocationDTO location;

    public EmployeeDTO(Employee entity) {
        super(entity);

        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.birthDate = entity.getBirthDate();
        this.gender = entity.getGender();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.role = entity.getRole();

    }

    public EmployeeDTO(Employee entity, @Valid LocationDTO location) {
        this(entity);
        setLocation(location);
    }

    public void setLocation(@Valid LocationDTO location) {
        if (this.location != null) {
            throw new IllegalStateException("Location is already set");
        }
        this.location = location;
    }

    @Override
    public Employee toEntity() {
        return Employee.builder()
                .role(role)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender)
                .build();
    }
}
