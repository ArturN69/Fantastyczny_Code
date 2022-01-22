package pl.sda.springwebmvc.controller;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.springwebmvc.model.Book;
import pl.sda.springwebmvc.model.BookRepository;

import java.util.List;

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

    @PostMapping(value = "/add")
    public String addBook(@ModelAttribute Book book){
        books.save(book);
        return "redirect:/book/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        final List<Book> bookList = books.findAll();
        model.addAttribute("books", bookList);
        return "/book/list";
    }


}
