package at.spengergasse.library.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {

    int PostalCode;
    int houseNumber;
    String City;
    String Street;
    String Country;

}
