package ru.vsu.cs.volchenko.site.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.tuple.entity.EntityMetamodel;

import java.sql.Blob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("photo")
@Entity
@IdClass(PhotoId.class)
@Table(name = "photo")
public class Photo {

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @Id
    @Column(name = "position")
    private int position;

    @Lob
    @Column(name = "product_photo")
    private Blob photo;


}
