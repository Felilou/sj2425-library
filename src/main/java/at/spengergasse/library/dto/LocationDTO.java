package at.spengergasse.library.dto;

import at.spengergasse.library.model.Location;
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
    private final List<LocationDTO> connectionsTo;
    private final List<BookDTO> books;
    private final List<EmployeeDTO> employees;

    public LocationDTO(Location entity) {
        super(entity);
        this.type = entity.getType();
        this.connectionsTo = entity.getConnectionsTo().stream().map(LocationDTO::new).toList();
        this.books = entity.getBooks().stream().map(BookDTO::new).toList();
        this.employees = entity.getEmployees().stream().map(EmployeeDTO::new).toList();
    }

    @Override
    Location toEntity() {
        return Location.builder().type(type).build();
    }
}
