package at.spengergasse.library.dto;

import at.spengergasse.library.model.AbstractEntity;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public abstract class AbstractDTO<E extends AbstractEntity> {

    private final UUID uuid;

    public abstract E toEntity();

    public AbstractDTO(E entity) {
        this.uuid = entity.getUuid();
    }

}
