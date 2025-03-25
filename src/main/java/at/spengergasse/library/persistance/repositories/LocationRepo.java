package at.spengergasse.library.persistance.repositories;

import at.spengergasse.library.model.Location;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends UUIDRepository<Location> {
}
