package ru.vsu.cs.volchenko.site.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Phone number should not be empty")
    @Pattern(regexp="(^$|\\+?[0-9]{11})")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Post index should not be empty")
    @Column(name = "post_index")
    private String postIndex;

    @NotEmpty(message = "Country should not be empty")
    @Column(name = "country")
    private String country = "Russia";

    @NotEmpty(message = "City should not be empty")
    @Column(name = "city")
    private String city;

    @NotEmpty(message = "Address should not be empty")
    @Column(name = "address")
    private String address;

    @Column(name = "comment")
    private String comment;

    @Min(value = 0, message = "Ship price should be greater than 0")
    @Column(name = "ship_price")
    private int shipPrice;

    @Column(name = "state", columnDefinition = "ENUM('CREATED', 'DELIVERING', 'DONE', 'CANCELLED')")
    @Enumerated(EnumType.STRING)
    private OrderState orderState = OrderState.CREATED;

    @Column(name = "promocode")
    private String promoCode;

    @OneToMany(mappedBy = "orderDetails", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderedProduct> orderedProducts;

}
