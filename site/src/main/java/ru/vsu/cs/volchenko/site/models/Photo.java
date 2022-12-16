package ru.vsu.cs.volchenko.site.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Blob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    private int productId;

    private int position;

    @NotNull(message = "Photo can't be null")
    private Blob productPhoto;

}
