package dematic.demo.repositories;

import dematic.demo.models.Book;


public class BookRepository extends AbstractBookRepository<Book> {
    public BookRepository(String tableName) {
        super(tableName);
    }
}
