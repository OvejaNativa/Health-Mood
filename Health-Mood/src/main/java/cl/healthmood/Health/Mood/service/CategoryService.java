package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category save(Category category);

    Optional<Category> findById(Integer id);

    List<Category> findAll();

    Page<Category> findAll(Pageable pageable);

    Category update(Integer id, Category category);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    List<Category> findByNameContainingIgnoreCase(String name);

    Optional<Category> findByName(String name);

    boolean existsByName(String name);

    List<Category> findAllWithProducts();

    long count();
}