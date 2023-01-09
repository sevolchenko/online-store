package ru.vsu.cs.volchenko.site.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.volchenko.site.entity.OrderState;
import ru.vsu.cs.volchenko.site.entity.OrderedProduct;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Phone number should not be empty")
    @Pattern(regexp="(^$|\\+?[0-9]{11})")
    private String phoneNumber;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Post index should not be empty")
    private String postIndex;

    @NotEmpty(message = "Country should not be empty")
    private String country;

    @NotEmpty(message = "City should not be empty")
    private String city;

    @NotEmpty(message = "Address should not be empty")
    private String address;

    private String comment;

    private String promoCode;
}
