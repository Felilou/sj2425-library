package at.spengergasse.library.persistance.repositories;

import at.spengergasse.library.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends UUIDRepository<Book> {

    Optional<Book> findByTitle(String title);
    Optional<Book> findByTitleIgnoreCase(String title);
    List<Book> findByTitleContainsIgnoreCase(String title);
}
