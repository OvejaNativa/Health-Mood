package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.model.Category;
import cl.healthmood.Health.Mood.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category save(Category category) {
        log.debug("Guardando categoría: {}", category.getName());
        validateCategory(category);

        if (existsByName(category.getName())) {
            throw new IllegalArgumentException("Ya existe una categoría con el nombre: " + category.getName());
        }

        return categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findById(Integer id) {
        log.debug("Buscando categoría por ID: {}", id);
        return categoryRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        log.debug("Obteniendo todas las categorías");
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Category> findAll(Pageable pageable) {
        log.debug("Obteniendo categorías paginadas: {}", pageable);
        return categoryRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Category update(Integer id, Category category) {
        log.debug("Actualizando categoría ID: {}", id);

        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    updateCategoryFields(existingCategory, category);
                    return categoryRepository.save(existingCategory);
                })
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        log.debug("Eliminando categoría ID: {}", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));

        if (category.getProducts() != null && !category.getProducts().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar la categoría porque tiene productos asociados");
        }

        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return categoryRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findByNameContainingIgnoreCase(String name) {
        log.debug("Buscando categorías por nombre: {}", name);
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findByName(String name) {
        log.debug("Buscando categoría por nombre exacto: {}", name);
        return categoryRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAllWithProducts() {
        log.debug("Obteniendo categorías con productos");
        return categoryRepository.findAllWithProducts();
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return categoryRepository.count();
    }

    private void validateCategory(Category category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio");
        }

        if (category.getName().length() > 100) {
            throw new IllegalArgumentException("El nombre de la categoría no puede exceder 100 caracteres");
        }
    }

    private void updateCategoryFields(Category existing, Category updated) {
        if (updated.getName() != null && !updated.getName().trim().isEmpty()) {
            // Validar que el nuevo nombre no exista si es diferente al actual
            if (!existing.getName().equals(updated.getName()) && existsByName(updated.getName())) {
                throw new IllegalArgumentException("Ya existe una categoría con el nombre: " + updated.getName());
            }
            existing.setName(updated.getName().trim());
        }

        if (updated.getDescription() != null) {
            existing.setDescription(updated.getDescription());
        }
    }
}