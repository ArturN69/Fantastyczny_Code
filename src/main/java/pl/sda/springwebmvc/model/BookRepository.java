package pl.sda.springwebmvc.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository {

    private Map<String, Book> books = new HashMap<>();

    public void save(Book book){
        books.put(book.getIsbn(), book);
    }

    public List<Book> findAll(){
        return new ArrayList<>(books.values());
    }
}
