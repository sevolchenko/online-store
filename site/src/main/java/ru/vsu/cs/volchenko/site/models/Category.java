package ru.vsu.cs.volchenko.site.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private int categoryId;

    private int parentCategoryId;

    @NotEmpty(message = "Category name shouldn't be empty")
    private String name;

}
