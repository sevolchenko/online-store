package ru.vsu.cs.volchenko.site.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.volchenko.site.entity.Product;
import ru.vsu.cs.volchenko.site.entity.StateOfShownInfo;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByStateOfShownInfoNot(StateOfShownInfo stateOfShownInfo);
    Page<Product> findAllByStateOfShownInfoNot(StateOfShownInfo stateOfShownInfo, Pageable pageable);

    Long countByStateOfShownInfoNot(StateOfShownInfo stateOfShownInfo);

}