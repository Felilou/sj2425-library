package at.spengergasse.library.dto;

import at.spengergasse.library.model.Location;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class LocationDTO extends AbstractDTO<Location> {

    private final Location.Type type;
    @JsonManagedReference
    private final List<LocationDTO> connectionsTo;
    @JsonManagedReference
    private final List<BookDTO> books;
    @JsonManagedReference
    private final List<EmployeeDTO> employees;

    public LocationDTO(Location entity) {

        super(entity);
        this.type = entity.getType();
        this.connectionsTo = entity.getConnectionsTo().stream().map(LocationDTO::new).toList();

        this.books = entity.getBooks().stream().map(book -> {
            BookDTO bookDTO = new BookDTO(book);
            bookDTO.setLocation(this);
            return bookDTO;
        }).toList();

        this.employees = entity.getEmployees().stream().map(employee -> {
            EmployeeDTO employeeDTO = new EmployeeDTO(employee);
            employeeDTO.setLocation(this);
            return employeeDTO;
        }).toList();

    }

    @Override
    public Location toEntity() {
        return Location.builder().type(type).build();
    }

}
