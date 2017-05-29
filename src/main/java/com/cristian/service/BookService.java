package com.cristian.service;

import com.cristian.domain.Book;
import java.util.List;

/**
 * Created by cristian.petre on 5/28/17.
 */
public interface BookService {

    Iterable<Book> findAll();

    List<Book> search(String q);

    Book searchBy(int q);

    Book findOne(int id);

    void save(Book book);

    void delete(int id);

}