package ru.sberp.springgraphql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberp.springgraphql.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
