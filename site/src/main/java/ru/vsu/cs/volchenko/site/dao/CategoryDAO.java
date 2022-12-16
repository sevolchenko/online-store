package ru.vsu.cs.volchenko.site.dao;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vsu.cs.volchenko.site.models.Category;

import java.util.List;

@AllArgsConstructor(onConstructor=@__(@Autowired))
@Component
public class CategoryDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Category> get() {
        return jdbcTemplate.query("SELECT * FROM category", new BeanPropertyRowMapper<>(Category.class));
    }

    public Category get(int id) {
        return jdbcTemplate.query("SELECT * FROM category WHERE category_id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Category.class)).stream().findAny().orElse(null);
    }

    public void save(Category category) {
        jdbcTemplate.update("INSERT INTO category VALUES(NULL, ?, ?)", category.getCategoryId(), category.getName());
    }

    public void update(int id, Category updatedCategory) {
        jdbcTemplate.update("UPDATE category SET parent_category_id=?, name=? WHERE category_id=?",
                updatedCategory.getParentCategoryId(), updatedCategory.getName(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM category WHERE category_id=?", id);
    }

}
