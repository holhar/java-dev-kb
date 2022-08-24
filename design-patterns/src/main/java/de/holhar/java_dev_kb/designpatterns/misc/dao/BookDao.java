package de.holhar.java_dev_kb.designpatterns.misc.dao;

import java.util.List;

public interface BookDao {
    List<Book> findAll();
    Book findById(int isbn);
    void save(Book book);
    void delete(Book book);
}
