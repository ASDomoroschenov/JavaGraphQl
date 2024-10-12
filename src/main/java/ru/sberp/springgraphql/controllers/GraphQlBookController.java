package ru.sberp.springgraphql.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import ru.sberp.springgraphql.dto.CreateBookRequest;
import ru.sberp.springgraphql.models.Author;
import ru.sberp.springgraphql.models.Book;
import ru.sberp.springgraphql.services.BookService;

@Controller
@RequiredArgsConstructor
public class GraphQlBookController {

  private final BookService bookService;

  /**
   * Возвращает книгу по ее id
   *
   * @param id идентификатор книги
   * @return найденная книга
   */
  @QueryMapping
  public Book bookById(@Argument Integer id) {
    return bookService.bookById(id);
  }

  /**
   * Возвращает все книги
   *
   * @return список всех книг
   */
  @QueryMapping
  public List<Book> getAllBooks() {
    return bookService.getAllBooks();
  }

  /**
   * Создание книги
   *
   * @param request запрос на создание
   * @return созданная книга
   */
  @MutationMapping
  public Book createBook(@Argument("book") CreateBookRequest request) {
    return bookService.createBook(request);
  }

  /**
   * Удаление книги
   *
   * @param id идентификатор книги
   * @return удаленная книга
   */
  @MutationMapping
  public Book deleteBook(@Argument("id") Integer id) {
    return bookService.deleteBook(id);
  }

  /**
   * Данный метод предназначен для отображения внутренних полей модели Book
   *
   * @param book модель Book
   * @return внутреннее поле Author
   */
  @SchemaMapping
  public Author author(Book book) {
    return book.getAuthor();
  }
}
