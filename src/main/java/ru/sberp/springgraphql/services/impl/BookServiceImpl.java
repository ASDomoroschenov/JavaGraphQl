package ru.sberp.springgraphql.services.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberp.springgraphql.dto.CreateBookRequest;
import ru.sberp.springgraphql.models.Book;
import ru.sberp.springgraphql.repositories.AuthorRepository;
import ru.sberp.springgraphql.repositories.BookRepository;
import ru.sberp.springgraphql.services.BookService;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  @Override
  public Book bookById(Integer id) {
    return bookRepository.findById(id).orElse(null);
  }

  @Override
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  @Override
  public Book createBook(CreateBookRequest request) {
    return bookRepository.saveAndFlush(
        Book.builder()
            .id(request.getId())
            .name(request.getName())
            .pageCount(request.getPageCount())
            .author(authorRepository.findById(request.getAuthorId()).orElse(null))
            .build()
    );
  }

  @Override
  public Book deleteBook(Integer id) {
    Book bookToDelete = bookRepository.findById(id).orElse(null);
    bookRepository.deleteById(id);
    return bookToDelete;
  }
}
