package ru.sberp.springgraphql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberp.springgraphql.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
