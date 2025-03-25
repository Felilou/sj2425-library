package at.spengergasse.library.service;

import at.spengergasse.library.dto.AbstractDTO;
import at.spengergasse.library.model.AbstractEntity;
import at.spengergasse.library.persistance.repositories.UUIDRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public abstract class GenericService<E extends AbstractEntity, DTO extends AbstractDTO<E>> {

    public List<E> findAll() {
        return getUUIDRepository().findAll();
    }

    public E findByUUID(UUID uuid) {
        return getUUIDRepository().findByUUID(uuid).orElseThrow();
    }

    public E save(E entity) {
        return getUUIDRepository().save(entity);
    }

    public void delete(E entity) {
        getUUIDRepository().delete(entity);
    }

    public void deleteByUUID(UUID uuid) {
        getUUIDRepository().delete(findByUUID(uuid));
    }

    public abstract UUIDRepository<E> getUUIDRepository();

}
