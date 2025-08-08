package cl.healthmood.Health.Mood.service;
import cl.healthmood.Health.Mood.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    Optional<Product> findById(Integer id);

    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Product update(Integer id, Product product);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategoryId(Integer categoryId);

    List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice);

    List<Product> findByPriceGreaterThanEqual(Integer price);

    List<Product> findByPriceLessThanEqual(Integer price);

    long count();
}