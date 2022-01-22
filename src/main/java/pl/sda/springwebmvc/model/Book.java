package pl.sda.springwebmvc.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@Builder
public class Book {
    @NotNull(message = "Brak tytułu!")
    @Length(min = 1, message = "Tytuł musi posiadać choć jeden znak!")
    private String title;

    @NotNull
    @NotBlank
    private String authors;

    @PositiveOrZero(message = "Cena nie może być ujemna!")
    @NotNull(message = "Musisz podać cenę!")
    private BigDecimal price;
    @ISBN
    private String isbn;

    @Range(min = 1950, max = 2050)
    private int publishingYear;

    @NotNull
    private String publisher;

    private Boolean firstEdition;

    public boolean isFirstEdition(){
        return firstEdition == null ? false : firstEdition;
    }
}
