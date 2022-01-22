package pl.sda.springwebmvc.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Book {
    private String title;
    private String authors;
    private BigDecimal price;
    private String isbn;
    private int publishingYear;
    private String publisher;
    private boolean firstEdition;
}
