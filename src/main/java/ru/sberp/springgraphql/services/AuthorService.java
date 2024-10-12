package ru.sberp.springgraphql.services;

import java.util.List;
import ru.sberp.springgraphql.dto.CreateAuthorRequest;
import ru.sberp.springgraphql.models.Author;

public interface AuthorService {

  Author authorById(Integer id);

  List<Author> getAllAuthors();

  Author createAuthor(CreateAuthorRequest request);

  Author deleteAuthor(Integer id);
}
