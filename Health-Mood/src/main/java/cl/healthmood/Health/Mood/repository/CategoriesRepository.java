package cl.healthmood.Health.Mood.repository;

import cl.healthmood.Health.Mood.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
    Integer id(Integer id);
}
