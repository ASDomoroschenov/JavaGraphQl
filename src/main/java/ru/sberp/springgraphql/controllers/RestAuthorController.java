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
import ru.sberp.springgraphql.dto.CreateAuthorRequest;
import ru.sberp.springgraphql.models.Author;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class RestAuthorController {

  private final GraphQlClient graphQlClient;

  @GetMapping("/get-by-id")
  public Author getAuthorById(@RequestParam("id") Long id) {
    String graphQlRequest = """
        query GetAuthorById($id: ID!) {
          authorById(id: $id) {
            id
            firstName
            lastName
          }
        }
        """;
    return graphQlClient.document(graphQlRequest)
        .variable("id", id)
        .retrieveSync("authorById")
        .toEntity(Author.class);
  }

  @PostMapping("/create")
  public Author createAuthor(@RequestBody CreateAuthorRequest request) {
    String graphQlRequest = """
        mutation CreateNewAuthor($author: CreateAuthorRequest!) {
          createAuthor(author: $author) {
            id
            firstName
            lastName
          }
        }
        """;
    return graphQlClient.document(graphQlRequest)
        .variable("author", request)
        .retrieveSync("createAuthor")
        .toEntity(Author.class);
  }

  @DeleteMapping("/delete")
  public Author deleteAuthor(@RequestParam Integer id) {
    String graphQlRequest = """
        mutation DeleteAuthor($id: ID!) {
          deleteAuthor(id: $id) {
            id
            firstName
            lastName
          }
        }
        """;
    return graphQlClient.document(graphQlRequest)
        .variable("id", id)
        .retrieveSync("deleteAuthor")
        .toEntity(Author.class);
  }
}
