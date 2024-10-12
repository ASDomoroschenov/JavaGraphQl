package ru.sberp.springgraphql.services.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberp.springgraphql.dto.CreateAuthorRequest;
import ru.sberp.springgraphql.models.Author;
import ru.sberp.springgraphql.repositories.AuthorRepository;
import ru.sberp.springgraphql.services.AuthorService;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public Author authorById(Integer id) {
    return authorRepository.findById(id).orElse(null);
  }

  @Override
  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }

  @Override
  public Author createAuthor(CreateAuthorRequest request) {
    return authorRepository.saveAndFlush(
        Author.builder()
            .id(request.getId())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .build()
    );
  }

  @Override
  public Author deleteAuthor(Integer id) {
    Author authorToDelete = authorRepository.findById(id).orElse(null);
    authorRepository.deleteById(id);
    return authorToDelete;
  }
}
