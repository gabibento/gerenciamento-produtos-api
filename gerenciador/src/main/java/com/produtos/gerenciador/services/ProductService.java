package com.produtos.gerenciador.services;

import com.produtos.gerenciador.entities.Product;
import com.produtos.gerenciador.exceptions.ProductNotFoundException;
import com.produtos.gerenciador.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Product not found with id: ";

    @Autowired
    private ProductRepository repository;

    @Transactional
    public Product insert(Product product) {
        return repository.save(product);
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + id));
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> findByName(String name) {
        return repository.findAll().stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public List<Product> findByCategory(String category) {
        return repository.findAll().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @Transactional
    public Product update(Long id, Product product) {
        Product productFound = findById(id);
        productFound.setName(product.getName());
        productFound.setCategory(product.getCategory());
        productFound.setPrice(product.getPrice());
        productFound.setQuantity(product.getQuantity());

        return repository.save(productFound);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + id);
        }
        repository.deleteById(id);
    }
}
