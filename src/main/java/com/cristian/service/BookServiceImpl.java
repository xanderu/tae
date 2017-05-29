package com.cristian.service;

import com.cristian.domain.Book;
import com.cristian.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cristian.petre on 5/28/17.
 */

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> search(String q) {
        return bookRepository.findByTitle(q);
    }

    @Override
    public Book searchBy(int q) {
        return bookRepository.findById(q).get(0);
    }

    @Override
    public Book findOne(int id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(int id) {
        bookRepository.delete(id);

    }
}
