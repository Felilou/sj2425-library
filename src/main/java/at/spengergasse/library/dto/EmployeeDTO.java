package at.spengergasse.library.dto;

import at.spengergasse.library.model.Employee;
import at.spengergasse.library.model.Person;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private String email;

    private String phone;

    public EmployeeDTO(Employee entity) {
        super(entity);
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.birthDate = entity.getBirthDate();
        this.gender = entity.getGender();
    }

    @Override
    Employee toEntity() {
        return Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender)
                .build();
    }
}
