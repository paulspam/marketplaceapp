package com.intellias.intellistart.marketplaceapp.repository;

import java.util.List;

import com.intellias.intellistart.marketplaceapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    List<Product> findAll();

    Product findProductById(Long id);
}
