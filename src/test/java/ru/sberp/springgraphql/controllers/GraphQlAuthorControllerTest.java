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
import ru.sberp.springgraphql.controllers.GraphQlAuthorController;
import ru.sberp.springgraphql.dto.CreateAuthorRequest;
import ru.sberp.springgraphql.models.Author;
import ru.sberp.springgraphql.services.AuthorService;
import ru.sberp.springgraphql.utils.TestUtils;

@GraphQlTest(GraphQlAuthorController.class)
class GraphQlAuthorControllerTest {

  @MockBean
  private AuthorService authorService;

  @Autowired
  private GraphQlTester graphQlTester;

  @Test
  @DisplayName("Тест на поиск автора с помощью GraphQL")
  void givenBookId_whenGetBookById_thenReturnBook() {
    String graphQlQuery = """
        query GetAuthorById($id: ID!) {
          authorById(id: $id) {
            id
            firstName
            lastName
          }
        }
        """;
    Author expectedAuthor = Author.builder()
        .id(1)
        .firstName("Alexandr")
        .lastName("Domoroschenov")
        .build();
    when(authorService.authorById(1)).thenReturn(expectedAuthor);

    Author response = graphQlTester
        .document(graphQlQuery)
        .variable("id", 1)
        .execute()
        .path("authorById")
        .entity(Author.class)
        .get();

    assertEquals(response, expectedAuthor);
  }

  @Test
  @DisplayName("Тест на создание автора с помощью GraphQL")
  void givenCreateBookRequest_whenCreateBook_thenReturnBook() {
    String graphQlQuery = """
        mutation CreateNewAuthor($author: CreateAuthorRequest!) {
          createAuthor(author: $author) {
            id
            firstName
            lastName
          }
        }
        """;
    CreateAuthorRequest createAuthorRequest = CreateAuthorRequest.builder()
        .id(1)
        .firstName("Alexandr")
        .lastName("Domoroshenov")
        .build();
    Author expectedAuthor = Author.builder()
        .id(1)
        .firstName("Alexandr")
        .lastName("Domoroschenov")
        .build();
    when(authorService.createAuthor(any())).thenReturn(expectedAuthor);

    Author response = graphQlTester
        .document(graphQlQuery)
        .variable("author", TestUtils.toMap(createAuthorRequest))
        .execute()
        .path("createAuthor")
        .entity(Author.class)
        .get();

    assertEquals(response, expectedAuthor);
  }

  @Test
  @DisplayName("Тест на удаление автора с помощью GraphQL")
  void givenBookId_whenDeleteBook_thenDeleteAndReturnBook() {
    String graphQlQuery = """
        mutation DeleteAuthor($id: ID!) {
          deleteAuthor(id: $id) {
            id
            firstName
            lastName
          }
        }
        """;
    Author expectedAuthor = Author.builder()
        .id(1)
        .firstName("Alexandr")
        .lastName("Domoroschenov")
        .build();
    when(authorService.deleteAuthor(1)).thenReturn(expectedAuthor);

    Author response = graphQlTester
        .document(graphQlQuery)
        .variable("id", 1)
        .execute()
        .path("deleteAuthor")
        .entity(Author.class)
        .get();

    assertEquals(response, expectedAuthor);
  }
}
