package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.model.Product;
import cl.healthmood.Health.Mood.repository.ProductRepository;
import cl.healthmood.Health.Mood.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product save(Product product) {
        log.debug("Guardando producto: {}", product.getName());
        validateProduct(product);
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Integer id) {
        log.debug("Buscando producto por ID: {}", id);
        return productRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        log.debug("Obteniendo todos los productos");
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        log.debug("Obteniendo productos paginados: {}", pageable);
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Product update(Integer id, Product product) {
        log.debug("Actualizando producto ID: {}", id);

        return productRepository.findById(id)
                .map(existingProduct -> {
                    updateProductFields(existingProduct, product);
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        log.debug("Eliminando producto ID: {}", id);

        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }

        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return productRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByNameContainingIgnoreCase(String name) {
        log.debug("Buscando productos por nombre: {}", name);
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByCategoryId(Integer categoryId) {
        log.debug("Buscando productos por categoría ID: {}", categoryId);
        return productRepository.findByCategoryCategoryId(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice) {
        log.debug("Buscando productos en rango de precios: {} - {}", minPrice, maxPrice);
        validatePriceRange(minPrice, maxPrice);
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByPriceGreaterThanEqual(Integer price) {
        log.debug("Buscando productos con precio >= {}", price);
        return productRepository.findByPriceGreaterThanEqual(price);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByPriceLessThanEqual(Integer price) {
        log.debug("Buscando productos con precio <= {}", price);
        return productRepository.findByPriceLessThanEqual(price);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return productRepository.count();
    }

    private void validateProduct(Product product) {
        System.out.println("precio recibido: "+ product.getPrice());
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if (product.getPrice() == null || product.getPrice() < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor o igual a 0");
        }
    }

    private void validatePriceRange(Integer minPrice, Integer maxPrice) {
        if (minPrice == null || maxPrice == null) {
            throw new IllegalArgumentException("Los precios mínimo y máximo son obligatorios");
        }
        if (minPrice < 0 || maxPrice < 0) {
            throw new IllegalArgumentException("Los precios no pueden ser negativos");
        }
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("El precio mínimo no puede ser mayor al máximo");
        }
    }

    private void updateProductFields(Product existing, Product updated) {
        if (updated.getName() != null && !updated.getName().trim().isEmpty()) {
            existing.setName(updated.getName().trim());
        }
        if (updated.getDescription() != null) {
            existing.setDescription(updated.getDescription());
        }
        if (updated.getPrice() != null && updated.getPrice() >= 0) {
            existing.setPrice(updated.getPrice());
        }
        if (updated.getCategory() != null) {
            existing.setCategory(updated.getCategory());
        }
    }
}