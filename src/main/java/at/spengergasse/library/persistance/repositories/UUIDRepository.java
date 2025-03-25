package at.spengergasse.library.persistance.repositories;

import at.spengergasse.library.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface UUIDRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {

    @Query("SELECT e FROM #{#entityName} e WHERE e.uuid = :uuid")
    Optional<E> findByUUID(@Param("uuid") UUID uuid);

}
