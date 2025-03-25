package at.spengergasse.library.service;

import at.spengergasse.library.dto.LocationDTO;
import at.spengergasse.library.model.Location;
import at.spengergasse.library.persistance.repositories.LocationRepo;
import at.spengergasse.library.persistance.repositories.UUIDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocationService extends GenericService<Location, LocationDTO> {

    private LocationRepo locationRepo;

    @Autowired
    public LocationService(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    @Override
    public UUIDRepository<Location> getUUIDRepository() {
        return locationRepo;
    }

    public List<Location> findRouteToFromTo(UUID from, UUID to) {
        //TODO: Implement this method
        return null;
    }

}
