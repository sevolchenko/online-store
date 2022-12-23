package ru.vsu.cs.volchenko.site.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.volchenko.site.config.MvcConfig;
import ru.vsu.cs.volchenko.site.entity.Photo;
import ru.vsu.cs.volchenko.site.entity.Product;
import ru.vsu.cs.volchenko.site.repositories.PhotosRepository;
import ru.vsu.cs.volchenko.site.repositories.ProductsRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final PhotosRepository photoRepository;

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Product findOne(int id) {
        Product product = productsRepository.findById(id).orElse(null);
        product.getPhotos().forEach(photo -> {
            Blob blob = photo.getPhoto();
            try {
                byte[] array = blob.getBytes(1, (int) blob.length());
                File file = new File(MvcConfig.SAVES_FOLDER.getPath(),
                        photo.getProduct().getId() + "_" + photo.getPosition());
                FileOutputStream out = new FileOutputStream(file);
                file.deleteOnExit();
                out.write(array);
                out.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
        return product;
    }

    @Transactional
    public void save(Product product) {
        product.setReleaseDate(LocalDate.now());
        product.setCurrentPrice(product.getInitialPrice());
        if (product.getPhotos() == null) {
            product.setPhotos(new ArrayList<>());
        } else {
            IntStream.range(0, product.getPhotos().size())
                    .forEach(idx -> {
                        Photo photo = product.getPhotos().get(idx);
                        photo.setProduct(product);
                    });
        }
        productsRepository.save(product);
        photoRepository.saveAll(product.getPhotos());
    }

    @Transactional
    public void update(int id, Product updatedProduct) {
        updatedProduct.setId(id);
        if (updatedProduct.getPhotos() == null) {
            updatedProduct.setPhotos(new ArrayList<>());
        } else {
            IntStream.range(0, updatedProduct.getPhotos().size())
                    .forEach(idx -> {
                        Photo photo = updatedProduct.getPhotos().get(idx);
                        photo.setProduct(updatedProduct);
                    });
        }
        productsRepository.save(updatedProduct);
        photoRepository.saveAll(updatedProduct.getPhotos());
    }

    @Transactional
    public void delete(int id) {
        productsRepository.deleteById(id);
    }

}