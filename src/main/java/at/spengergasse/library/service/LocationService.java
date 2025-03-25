package at.spengergasse.library.service;

import at.spengergasse.library.dto.LocationDTO;
import at.spengergasse.library.model.Location;
import at.spengergasse.library.persistance.repositories.LocationRepo;
import at.spengergasse.library.persistance.repositories.UUIDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocationService extends GenericService<Location, LocationDTO> {

    private final LocationRepo locationRepo;

    @Autowired
    public LocationService(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    @Override
    public UUIDRepository<Location> getUUIDRepository() {
        return locationRepo;
    }

    public List<Location> findRouteToFromTo(UUID from, UUID to) {
        Location fromLocation = findByUUID(from);
        Location toLocation = findByUUID(to);

        if (fromLocation == null || toLocation == null) {
            return List.of();
        }

        if (from.equals(to)) {
            return List.of(fromLocation);
        }

        Queue<Location> queue = new LinkedList<>();
        Map<Location, Location> parentMap = new HashMap<>();
        Set<Location> visited = new HashSet<>();

        queue.add(fromLocation);
        visited.add(fromLocation);

        while (!queue.isEmpty()) {
            Location current = queue.poll();

            if (current.equals(toLocation)) {
                return reconstructPath(parentMap, fromLocation, toLocation);
            }

            for (Location neighbor : current.getConnectionsTo()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return List.of();
    }

    private List<Location> reconstructPath(Map<Location, Location> parentMap, Location start, Location end) {
        List<Location> path = new ArrayList<>();
        Location current = end;

        while (current != null) {
            path.addFirst(current);
            current = parentMap.get(current);
        }

        return path;
    }

    private void addConnection(UUID from, UUID to) {
        Location fromLocation = findByUUID(from);
        Location toLocation = findByUUID(to);

        fromLocation.addConnectionTo(toLocation);
    }

}
