package ru.sberp.springgraphql.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.sberp.springgraphql.dto.CreateAuthorRequest;
import ru.sberp.springgraphql.models.Author;
import ru.sberp.springgraphql.services.AuthorService;

@Controller
@RequiredArgsConstructor
public class GraphQlAuthorController {

  private final AuthorService authorService;

  /**
   * Возвращает автора по его id
   *
   * @param id идентификатор автора
   * @return найденный автор
   */
  @QueryMapping
  public Author authorById(@Argument Integer id) {
    return authorService.authorById(id);
  }

  /**
   * Возвращает всех авторов
   *
   * @return список всех авторов
   */
  @QueryMapping
  public List<Author> getAllAuthors() {
    return authorService.getAllAuthors();
  }

  /**
   * Создание автора
   *
   * @param request запрос на создание
   * @return созданный автор
   */
  @MutationMapping
  public Author createAuthor(@Argument("author") CreateAuthorRequest request) {
    return authorService.createAuthor(request);
  }

  /**
   * Удаление автора
   *
   * @param id идентификатор автора
   * @return удаленный автор
   */
  @MutationMapping
  public Author deleteAuthor(@Argument("id") Integer id) {
    return authorService.deleteAuthor(id);
  }
}
