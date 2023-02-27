package ru.vsu.cs.volchenko.site.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.volchenko.site.entity.Category;
import ru.vsu.cs.volchenko.site.repositories.CategoriesRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    public List<Category> findAll() {
        return categoriesRepository.findAll();
    }

}
