package ru.sberp.springgraphql.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sberp.springgraphql.dto.CreateBookRequest;
import ru.sberp.springgraphql.models.Book;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class RestBookController {

  private final GraphQlClient graphQlClient;

  @GetMapping("/get-by-id")
  public Book getBookById(@RequestParam("id") Long id) {
    String graphQlRequest = """
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
    return graphQlClient.document(graphQlRequest)
        .variable("id", id)
        .retrieveSync("bookById")
        .toEntity(Book.class);
  }

  @PostMapping("/create")
  public Book createBook(@RequestBody CreateBookRequest request) {
    String graphQlRequest = """
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
    return graphQlClient.document(graphQlRequest)
        .variable("book", request)
        .retrieveSync("createBook")
        .toEntity(Book.class);
  }

  @DeleteMapping("/delete")
  public Book deleteBook(@RequestParam("id") Integer id) {
    String graphQlRequest = """
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
    return graphQlClient.document(graphQlRequest)
        .variable("id", id)
        .retrieveSync("deleteBook")
        .toEntity(Book.class);
  }
}
