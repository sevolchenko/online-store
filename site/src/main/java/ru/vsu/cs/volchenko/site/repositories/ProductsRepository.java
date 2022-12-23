package ru.vsu.cs.volchenko.site.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.volchenko.site.entity.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
}