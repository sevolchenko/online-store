package ru.vsu.cs.volchenko.site.dao;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vsu.cs.volchenko.site.models.Product;

import java.util.List;

@AllArgsConstructor(onConstructor=@__(@Autowired))
@Component
public class ProductDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Product> get() {
        return jdbcTemplate.query("SELECT * FROM product", new BeanPropertyRowMapper<>(Product.class));
    }

    public Product get(int id) {
        return jdbcTemplate.query("SELECT * FROM product WHERE product_id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Product.class)).stream().findAny().orElse(null);
    }

    public void save(Product product) {
        jdbcTemplate.update("INSERT INTO product VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                product.getName(), product.getDescription(), product.getCategoryId(), product.getColor(),
                product.getMaterials(), product.getInitialPrice(), product.getCurrentPrice(),
                product.getReleaseDate(), product.getStateOfShownInfo());
    }

    public void update(int id, Product updatedProduct) {
        jdbcTemplate.update("UPDATE product SET name=?, description=?, category_id=?, color=?," +
                        "materials=?, initial_price=?, current_price=?, release_date=?, state_of_shown_info=? " +
                        "WHERE product_id=?",
                updatedProduct.getName(), updatedProduct.getDescription(), updatedProduct.getCategoryId(),
                updatedProduct.getColor(), updatedProduct.getMaterials(), updatedProduct.getInitialPrice(),
                updatedProduct.getCurrentPrice(), updatedProduct.getReleaseDate(), updatedProduct.getStateOfShownInfo(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM product WHERE product_id=?", id);
    }

}
