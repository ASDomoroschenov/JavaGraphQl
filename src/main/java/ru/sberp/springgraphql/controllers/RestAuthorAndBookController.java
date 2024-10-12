package ru.sberp.springgraphql.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/author-and-book")
@RequiredArgsConstructor
public class RestAuthorAndBookController {

  private final GraphQlClient graphQlClient;

  @GetMapping("/get-by-id")
  public Mono<Object> getAuthorAndBookByOneRequest(
      @RequestParam("bookId") Integer bookId,
      @RequestParam("authorId") Integer authorId
  ) {
    String graphQlRequest = """
        query GetAuthorAndBook($authorId: ID!, $bookId: ID!) {
          authorById(id: $authorId) {
            id
            firstName
            lastName
          }
          bookById(id: $bookId) {
            id
            name
            pageCount
          }
        }
        """;
    return graphQlClient.document(graphQlRequest)
        .variable("authorId", authorId)
        .variable("bookId", bookId)
        .retrieve("")
        .toEntity(Object.class);
  }

}
