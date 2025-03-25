package at.spengergasse.library.presentation.api;

import at.spengergasse.library.dto.AbstractDTO;
import at.spengergasse.library.model.AbstractEntity;
import at.spengergasse.library.service.GenericService;

public abstract class GenericRestApiController<E extends AbstractEntity, DTO extends AbstractDTO<E>> {

    public abstract GenericService<E, DTO> getService();
    public abstract DTO toDTO(E entity);

}
