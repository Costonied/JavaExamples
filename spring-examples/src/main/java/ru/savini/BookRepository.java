package ru.savini;

import org.springframework.data.repository.CrudRepository;
import ru.savini.domain.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
}
