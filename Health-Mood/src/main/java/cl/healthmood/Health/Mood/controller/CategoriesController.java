package cl.healthmood.Health.Mood.controller;

import cl.healthmood.Health.Mood.model.Categories;
import cl.healthmood.Health.Mood.service.CategoriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;

    @GetMapping
    public List<Categories> listcategories(){
        return categoriesService.listCategories();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Categories> getById(@PathVariable Integer id){
        return categoriesService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Categories> store(@Valid @RequestBody Categories categories){
        return ResponseEntity.ok(categoriesService.store(categories));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Categories> upDate(@PathVariable Integer id, @Valid @RequestBody Categories categories){
        return ResponseEntity.ok(categoriesService.upDate(id, categories));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        categoriesService.delete(id);
        return ResponseEntity.ok().build();
    }

}
