package com.cristian.controller;

import com.cristian.domain.Book;
import com.cristian.domain.BookTo;
import com.cristian.domain.Response;
import com.cristian.service.BookService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by cristian.petre on 5/28/17.
 */

@RestController
@RequestMapping("/rest")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public Iterable<Book> index() {
        return bookService.findAll();
    }

    @GetMapping("/book/create")
    public Response create(@RequestParam String title, @RequestParam String author, @RequestParam String content) {
        Book book = new Book(title, author, content);
        bookService.save(book);
        return new Response("created");
    }

    @GetMapping("/book/{id}/edit")
    public Book edit(@PathVariable int id, @RequestParam String title, @RequestParam String author, @RequestParam String content) {
        Book existingBook = bookService.findOne(id);
        existingBook.setAuthor(author);
        existingBook.setTitle(title);
        existingBook.setContent(content);
        bookService.save(existingBook);
        return existingBook;
    }


    @GetMapping("/book/{id}/delete")
    public Response delete(@PathVariable int id) {
        bookService.delete(id);
        return new Response("deleted");
    }

    @GetMapping("/book/search")
    public List<Book> search(@RequestParam String q) {
        return bookService.search(q);
    }

    @GetMapping("/book/searchBy")
    public Book searchBy(@RequestParam int q) {
        Book book = bookService.searchBy(q);
        System.out.println(book.toString());
        return book;
    }
}
