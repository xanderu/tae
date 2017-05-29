package com.cristian.controller;

import com.cristian.domain.Book;
import com.cristian.domain.BookTo;
import com.cristian.domain.Response;
import com.cristian.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by cristian.petre on 5/28/17.
 */

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public String index(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        List<Book> books = restTemplate.getForObject("http://localhost:8080/rest/book", List.class);

        model.addAttribute("books", books);
        return "list2";
    }

    @GetMapping("/book/create")
    public String create(Model model) {
        model.addAttribute("book", new Book());
        return "form2";
    }

    @GetMapping("/book/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        BookTo book = restTemplate.getForObject("http://localhost:8080/rest/book/searchBy?q="+id, BookTo.class);

        model.addAttribute("book", book);
        return "form3";
    }

    @PostMapping("/book/save")
    public String save(@Valid BookTo book, BindingResult result, RedirectAttributes redirect) {

        RestTemplate restTemplate = new RestTemplate();
        System.out.println(book.toString());
        Response response = restTemplate.getForObject("http://localhost:8080/rest/book/create?title="+book.getTitle()+"&author="+book.getAuthor()+"&content="+book.getContent(), Response.class);

        if (result.hasErrors()) {
            return "form2";
        }
        redirect.addFlashAttribute("success", "Saved book successfully!");
        return "redirect:/book";
    }

    @PostMapping("/book/update")
    public String update(@Valid BookTo book, BindingResult result, RedirectAttributes redirect) {
        System.out.println("NotRest"+book.toString());
        RestTemplate restTemplate = new RestTemplate();
        BookTo responseBook = restTemplate.getForObject("http://localhost:8080/rest/book/"+book.getId()+"/edit?title="+book.getTitle()+"&author="+book.getAuthor()+"&content="+book.getContent(), BookTo.class);
        System.out.println("Rest"+responseBook.toString());
        if (result.hasErrors()) {
            return "form3";
        }
        redirect.addFlashAttribute("success", "Saved book successfully!");
        return "redirect:/book";
    }

    @GetMapping("/book/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {

        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.getForObject("http://localhost:8080/rest/book/"+id+"/delete", Response.class);

        redirect.addFlashAttribute("success", "Deleted book successfully!");
        return "redirect:/book";
    }

    @GetMapping("/book/search")
    public String search(@RequestParam("q") String q, Model model) {

        RestTemplate restTemplate = new RestTemplate();
        List<Book> books = restTemplate.getForObject("http://localhost:8080/rest/book/search?q="+q, List.class);


        if (q.equals("")) {
            return "redirect:/book";
        }

        model.addAttribute("books", books);
        return "list2";
    }
}
