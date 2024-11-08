package com.produtos.gerenciador.repositories;

import com.produtos.gerenciador.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
