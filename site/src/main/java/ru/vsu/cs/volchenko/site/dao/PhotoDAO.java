package ru.vsu.cs.volchenko.site.dao;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vsu.cs.volchenko.site.models.Photo;

import java.util.List;

@AllArgsConstructor(onConstructor=@__(@Autowired))
@Component
public class PhotoDAO {

    private final JdbcTemplate jdbcTemplate;

    public Photo get(int productId, int position) {
        return jdbcTemplate.query("SELECT * FROM photo WHERE product_id=? AND position=?", new Object[]{productId, position},
                new BeanPropertyRowMapper<>(Photo.class)).stream().findAny().orElse(null);
    }

    public List<Photo> get(int productId) {
        return jdbcTemplate.query("SELECT * FROM photo WHERE product_id=?", new Object[]{productId},
                new BeanPropertyRowMapper<>(Photo.class));
    }

    public List<Photo> get() {
        return jdbcTemplate.query("SELECT * FROM photo", new BeanPropertyRowMapper<>(Photo.class));
    }

    public void save(int productId, Photo photo) {
        photo.setProductId(productId);
        photo.setPosition(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM photo WHERE product_id=?", Integer.class,
                productId) + 1);
        jdbcTemplate.update("INSERT INTO photo VALUES(?, ?, ?)", photo.getProductId(), photo.getPosition(),
                photo.getProductPhoto());
    }

    public void save(int productId, int position, Photo photo) {
        photo.setProductId(productId);
        photo.setPosition(position);
        jdbcTemplate.update("INSERT INTO photo VALUES(?, ?, ?)", photo.getProductId(), photo.getPosition(),
                photo.getProductPhoto());
    }

    public void update(int productId, int position, Photo photo) {
        jdbcTemplate.update("UPDATE photo SET product_photo=? WHERE product_id=? AND position=?",
                photo.getProductPhoto(), productId, position);
    }

    public void delete(int productId, int position) {
        jdbcTemplate.update("DELETE FROM photo WHERE product_id=? AND position=?", productId, position);
    }

}
