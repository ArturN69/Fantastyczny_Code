package pl.sda.springwebmvc.controller;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    @GetMapping("/add-th-form")
    public String showAddBookThForm(Model model){
        model.addAttribute("book", Book.builder()
                        .title("Nowa książka")
                .build());
        return "/book/th-add-form";
    }

    @PostMapping(value = "/add")
    public String addBook(@Validated @ModelAttribute Book book, BindingResult result){
        if (result.hasErrors()){
            return "/book/th-add-form";
        } else {
            books.save(book);
            return "redirect:/book/list";
        }
    }

    @GetMapping("/list")
    public String list(Model model){
        final List<Book> bookList = books.findAll();
        model.addAttribute("books", bookList);
        return "/book/list";
    }


}
