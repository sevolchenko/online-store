package ru.vsu.cs.volchenko.site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {

    private String name;

    private String phoneNumber;

    private String email;

    private String postIndex;

    private String country;

    private String city;

    private String address;

    private String comment;

    private String promoCode;

}
