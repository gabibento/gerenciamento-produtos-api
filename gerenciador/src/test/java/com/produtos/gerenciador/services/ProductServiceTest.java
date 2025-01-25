package com.produtos.gerenciador.services;

import com.produtos.gerenciador.entities.Product;
import com.produtos.gerenciador.exceptions.ProductNotFoundException;
import com.produtos.gerenciador.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Product mockProduct(Long id, String name, double price, int quantity, String category) {
        return new Product(id, name, price, quantity, category);
    }

    @Test
    void insert() {
        Product product = mockProduct(null, "Produto A", 50.0, 10, "Categoria A");
        when(repository.save(any(Product.class))).thenReturn(product);

        Product result = service.insert(product);

        assertNotNull(result);
        assertEquals(product.getName(), result.getName());
        verify(repository, times(1)).save(product);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testFindById_ProductFound() {
        Long productId = 1L;
        Product product = mockProduct(productId, "Produto A", 50.0, 10, "Categoria A");
        when(repository.findById(productId)).thenReturn(Optional.of(product));

        Product result = service.findById(productId);

        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals("Produto A", result.getName());
        verify(repository, times(1)).findById(productId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testFindById_ProductNotFound() {
        Long productId = 1L;
        when(repository.findById(productId)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> service.findById(productId));
        assertEquals("Product not found with id: 1", exception.getMessage());
        verify(repository, times(1)).findById(productId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testFindAll() {
        Product product1 = mockProduct(1L, "Produto A", 50.0, 10, "Categoria A");
        Product product2 = mockProduct(2L, "Produto B", 30.0, 5, "Categoria B");
        when(repository.findAll()).thenReturn(List.of(product1, product2));

        List<Product> products = service.findAll();

        assertEquals(2, products.size());
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testFindByName() {
        Product product1 = mockProduct(1L, "Produto A", 50.0, 10, "Categoria A");
        when(repository.findAll()).thenReturn(List.of(product1));

        List<Product> products = service.findByName("Produto A");

        assertEquals(1, products.size());
        assertEquals("Produto A", products.get(0).getName());
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testFindByName_NoMatch() {
        when(repository.findAll()).thenReturn(List.of());

        List<Product> products = service.findByName("Inexistente");

        assertTrue(products.isEmpty());
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testFindByCategory_ProductsFound() {
        Product product1 = mockProduct(1L, "Produto A", 50.0, 10, "Categoria A");
        Product product2 = mockProduct(2L, "Produto B", 30.0, 5, "Categoria A");
        when(repository.findAll()).thenReturn(List.of(product1, product2));

        List<Product> products = service.findByCategory("Categoria A");

        assertEquals(2, products.size());
        assertEquals("Categoria A", products.get(0).getCategory());
        assertEquals("Categoria A", products.get(1).getCategory());
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testFindByCategory_NoMatch() {
        when(repository.findAll()).thenReturn(List.of());

        List<Product> products = service.findByCategory("Categoria Inexistente");

        assertTrue(products.isEmpty());
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void update() {
        Long productId = 1L;
        Product existingProduct = mockProduct(productId, "Produto A", 50.0, 10, "Categoria A");
        Product updatedProduct = mockProduct(productId, "Produto Atualizado", 60.0, 15, "Categoria B");
        when(repository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(repository.save(any(Product.class))).thenReturn(updatedProduct);

        Product result = service.update(productId, updatedProduct);

        assertNotNull(result);
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getPrice(), result.getPrice());
        assertEquals(updatedProduct.getCategory(), result.getCategory());

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(repository, times(1)).findById(productId);
        verify(repository, times(1)).save(productCaptor.capture());
        verifyNoMoreInteractions(repository);
        
        Product capturedProduct = productCaptor.getValue();
        assertEquals(updatedProduct.getName(), capturedProduct.getName());
        assertEquals(updatedProduct.getPrice(), capturedProduct.getPrice());
        assertEquals(updatedProduct.getCategory(), capturedProduct.getCategory());
    }


    @Test
    void update_ProductNotFound() {
        Long productId = 1L;
        Product updatedProduct = mockProduct(productId, "Produto Atualizado", 60.0, 15, "Categoria B");

        when(repository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> service.update(productId, updatedProduct));
        verify(repository, times(1)).findById(productId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testDeleteById_ProductExists() {
        Long productId = 1L;
        when(repository.existsById(productId)).thenReturn(true);

        service.deleteById(productId);

        verify(repository, times(1)).existsById(productId);
        verify(repository, times(1)).deleteById(productId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testDeleteById_ProductNotFound() {
        Long productId = 1L;
        when(repository.existsById(productId)).thenReturn(false);

        assertThrows(ProductNotFoundException.class, () -> service.deleteById(productId));
        verify(repository, times(1)).existsById(productId);
        verifyNoMoreInteractions(repository);
    }
}
