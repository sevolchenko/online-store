package ru.vsu.cs.volchenko.site.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int productId;

    private int categoryId;

    @NotEmpty(message = "Product name shouldn't be empty")
    private String name;

    private String description;

    private String color;

    private String materials;

    @Min(value = 0, message = "Initial price should be grower than 0")
    private int initialPrice;

    private int currentPrice;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private StateOfShownInfo stateOfShownInfo;

    public enum StateOfShownInfo {
        HIDE_ALL,
        INFO_ONLY,
        ALL_VISIBLE;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

}
