package ru.sberp.springgraphql.services;

import java.util.List;
import ru.sberp.springgraphql.dto.CreateBookRequest;
import ru.sberp.springgraphql.models.Book;

public interface BookService {

  Book bookById(Integer id);

  List<Book> getAllBooks();

  Book createBook(CreateBookRequest request);

  Book deleteBook(Integer id);
}
