package pl.sda.springwebmvc.controller;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.springwebmvc.model.BookRepository;

@Controller
@RequestMapping(value = "/book")
public class BookController {

    private final BookRepository books;

    public BookController(BookRepository books) {
        this.books = books;
    }

    @GetMapping("/add-form")
    public String showAddBookForm(){
        return "/book/add-form";
    }
}
