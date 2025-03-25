package at.spengergasse.library.service;

import at.spengergasse.library.dto.BookDTO;
import at.spengergasse.library.model.Book;
import at.spengergasse.library.persistance.repositories.BookRepo;
import at.spengergasse.library.persistance.repositories.UUIDRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookService extends GenericService<Book, BookDTO> {

    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public UUIDRepository<Book> getUUIDRepository() {
        return bookRepo;
    }

    @Transactional
    public List<Book> findByTitleContains(String title) {
        if(title==null||title.isEmpty()){
            return bookRepo.findAll();
        }
        return bookRepo.findByTitleContainsIgnoreCase(title);
    }

}
