package at.spengergasse.library.service;

import at.spengergasse.library.dto.AuthorDTO;
import at.spengergasse.library.model.Author;
import at.spengergasse.library.persistance.repositories.AuthorRepo;
import at.spengergasse.library.persistance.repositories.UUIDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends GenericService<Author, AuthorDTO> {

    AuthorRepo authorRepo;

    @Autowired
    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public UUIDRepository<Author> getUUIDRepository() {
        return null;
    }


}
