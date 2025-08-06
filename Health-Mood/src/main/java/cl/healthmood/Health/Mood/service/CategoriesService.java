package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.model.Categories;

import java.util.List;
import java.util.Optional;

public interface CategoriesService {
    List<Categories> listCategories();
    Categories store(Categories categories);
    Categories upDate(Integer id, Categories categories);
    void delete(Integer id);
    Optional<Categories> getById(Integer id);
}
