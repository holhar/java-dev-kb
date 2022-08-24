package de.holhar.java_dev_kb.designpatterns.misc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDaoImpl implements BookDao {

    private final Map<Integer, Book> books;

    public BookDaoImpl() {
        this.books = new HashMap<>();
        books.put(1, new Book(1, "1984"));
        books.put(2, new Book(2, "Brave New World"));
        books.put(3, new Book(3, "Qualityland"));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public Book findById(int isbn) {
        return books.get(isbn);
    }

    @Override
    public void save(Book book) {
        books.put(book.getIsbn(), book);
    }

    @Override
    public void delete(Book book) {
        books.remove(book.getIsbn());
    }
}
