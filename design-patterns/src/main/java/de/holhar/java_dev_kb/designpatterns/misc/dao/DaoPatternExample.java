package de.holhar.java_dev_kb.designpatterns.misc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoPatternExample {

    private static final Logger logger = LoggerFactory.getLogger(DaoPatternExample.class);

    public static void main(String[] args) {
        BookDaoImpl bookDao = new BookDaoImpl();
        bookDao.findAll().forEach(b -> logger.info("{}", b));

        Book book = bookDao.findById(1);
        book.setTitle("Scharnow");
        bookDao.save(book);

        bookDao.findAll().forEach(b -> logger.info("{}", b));
    }
}
