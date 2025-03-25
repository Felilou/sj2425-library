package at.spengergasse.library.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@NoArgsConstructor
public abstract class Person extends AbstractEntity {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private String email;
    private String phone;

    public enum Gender {
        MALE,
        FEMALE,
        OTHER,
    }

    @Transient
    public Duration getAge() {
        return Duration.between(birthDate, LocalDate.now());
    }

    @Override
    protected boolean individualEquals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        Person person = (Person) other;
        return Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getLastName(), person.getLastName()) && Objects.equals(getBirthDate(), person.getBirthDate()) && getGender() == person.getGender();
    }

    @Override
    protected int individualHashCode() {
        return Objects.hash(super.hashCode(), getFirstName(), getLastName(), getBirthDate(), getGender());
    }

}
