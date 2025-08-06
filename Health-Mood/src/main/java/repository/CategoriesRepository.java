package repository;

import com.bootcamp.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
    Integer id(Integer id);
}
