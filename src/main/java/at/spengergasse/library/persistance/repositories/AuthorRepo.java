package at.spengergasse.library.persistance.repositories;

import at.spengergasse.library.model.Author;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends UUIDRepository<Author> {
}
