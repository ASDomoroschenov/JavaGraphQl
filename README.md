# JavaGraphQl
Для параллельного выполнения запросов необходимо возвращать CompleateFuture или просто Future:
```
@QueryMapping
public CompletableFuture<Author> authorById(@Argument Integer id) {
  return CompletableFuture.supplyAsync(() -> authorService.authorById(id));
}
```
https://www.graphql-java.com/blog/threads/
