package ru.sberp.springgraphql.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import ru.sberp.springgraphql.controllers.GraphQlBookController;
import ru.sberp.springgraphql.dto.CreateBookRequest;
import ru.sberp.springgraphql.models.Author;
import ru.sberp.springgraphql.models.Book;
import ru.sberp.springgraphql.services.BookService;
import ru.sberp.springgraphql.utils.TestUtils;

@GraphQlTest(GraphQlBookController.class)
class GraphQlBookControllerTest {

  @MockBean
  private BookService bookService;

  @Autowired
  private GraphQlTester graphQlTester;

  @Test
  @DisplayName("Тест на поиск книги с помощью GraphQL")
  void givenBookId_whenGetBookById_thenReturnBook() {
    String graphQlQuery = """
        query GetBookById($id: ID!) {
          bookById(id: $id) {
            id
            name
            pageCount
            author {
              id
              firstName
              lastName
            }
          }
        }
        """;
    Book expectedBook = Book.builder()
        .id(1)
        .name("Book1")
        .pageCount(1)
        .author(Author.builder()
            .id(1)
            .firstName("Alexandr")
            .lastName("Domoroschenov")
            .build()
        ).
        build();
    when(bookService.bookById(1)).thenReturn(expectedBook);

    Book response = graphQlTester
        .document(graphQlQuery)
        .variable("id", 1)
        .execute()
        .path("bookById")
        .entity(Book.class)
        .get();

    assertEquals(response, expectedBook);
  }

  @Test
  @DisplayName("Тест на создание книги с помощью GraphQL")
  void givenCreateBookRequest_whenCreateBook_thenReturnBook() {
    String graphQlQuery = """
        mutation CreateNewBook($book: CreateBookRequest!) {
          createBook(book: $book) {
            id
            name
            pageCount
            author {
              id
              firstName
              lastName
            }
          }
        }
        """;
    CreateBookRequest createBookRequest = CreateBookRequest.builder()
        .id(1)
        .name("Book1")
        .pageCount(1)
        .authorId(1)
        .build();
    Book expectedBook = Book.builder()
        .id(1)
        .name("Book1")
        .pageCount(1)
        .author(Author.builder()
            .id(1)
            .firstName("Alexandr")
            .lastName("Domoroschenov")
            .build()
        ).
        build();
    when(bookService.createBook(any())).thenReturn(expectedBook);

    Book response = graphQlTester
        .document(graphQlQuery)
        .variable("book", TestUtils.toMap(createBookRequest))
        .execute()
        .path("createBook")
        .entity(Book.class)
        .get();

    assertEquals(response, expectedBook);
  }

  @Test
  @DisplayName("Тест на удаление книги с помощью GraphQL")
  void givenBookId_whenDeleteBook_thenDeleteAndReturnBook() {
    String graphQlQuery = """
        mutation DeleteBook($id: ID!) {
          deleteBook(id: $id) {
            id
            name
            pageCount
            author {
              id
              firstName
              lastName
            }
          }
        }
        """;
    Book expectedBook = Book.builder()
        .id(1)
        .name("Book1")
        .pageCount(1)
        .author(Author.builder()
            .id(1)
            .firstName("Alexandr")
            .lastName("Domoroschenov")
            .build()
        ).
        build();
    when(bookService.deleteBook(1)).thenReturn(expectedBook);

    Book response = graphQlTester
        .document(graphQlQuery)
        .variable("id", 1)
        .execute()
        .path("deleteBook")
        .entity(Book.class)
        .get();

    assertEquals(response, expectedBook);
  }
}
