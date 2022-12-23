package ru.vsu.cs.volchenko.site.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotEmpty(message = "Name should not be empty")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "color")
    private String color;

    @Column(name = "materials")
    private String materials;

    @Min(value = 0, message = "Price should be greater than 0")
    @Column(name = "initial_price")
    private int initialPrice;

    @Min(value = 0, message = "Price should be greater than 0")
    @Column(name = "current_price")
    private int currentPrice;

    @Column(name = "release_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Column(name = "state_of_shown_info", columnDefinition = "ENUM('HIDE_ALL', 'INFO_ONLY', 'ALL_VISIBLE')")
    @Enumerated(EnumType.STRING)
    private StateOfShownInfo stateOfShownInfo = StateOfShownInfo.HIDE_ALL;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Photo> photos;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderedProduct> orderedProducts;
}
