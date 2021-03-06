package pl.sda.springwebmvc.controller;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.sda.springwebmvc.model.Book;
import pl.sda.springwebmvc.model.BookRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/admin/list")
    public String adminList(Model model){
        final List<Book> bookList = books.findAll();
        model.addAttribute("books", bookList);
        return "/book/admin-list";
    }

    @GetMapping("/admin/delete/{isbn}")
    public String deleteBook(@PathVariable String isbn, Model model){
        if (books.delete(isbn)){
            model.addAttribute("isbn", isbn);
            return "/book/admin-confirm-delete";
        } else {
            model.addAttribute("message", "Brak takiej książki! Usunięcie niemożliwe!");
            return "error";
        }
    }

    @GetMapping("/admin/update/{isbn}")
    public String updateBookForm(@PathVariable String isbn, Model model){
        final Optional<Book> optionalBook = books.findById(isbn);
        if (optionalBook.isPresent()){
            model.addAttribute("book", optionalBook.get());
            return "/book/admin-update-form";
        } else {
            model.addAttribute("message", "Nie można edytować, brak książki o podanym ISBN!");
            return "error";
        }
    }

    @PostMapping("/admin/update")
    public String updateBook(@Valid @ModelAttribute Book book, Errors errors, Model model){
        if(errors.hasErrors()){
            return "/book/admin-update-form";
        } else{
            if (books.update(book)){
                return "redirect:/book/admin/list";
            } else {
                model.addAttribute("message", "Próba edycji nieistniejącej książki!");
                return "error";
            }
        }
    }
}
