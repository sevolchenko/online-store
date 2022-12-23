package ru.vsu.cs.volchenko.site.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoId implements Serializable {

    private Product product;

    private int position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotoId photoId = (PhotoId) o;

        if (position != photoId.position) return false;
        return product.equals(photoId.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product != null ? product.getId() : 0, position);
    }
}
