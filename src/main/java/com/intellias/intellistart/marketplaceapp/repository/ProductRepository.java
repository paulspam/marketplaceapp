package com.intellias.intellistart.marketplaceapp.repository;

import com.intellias.intellistart.marketplaceapp.model.Product;
import com.intellias.intellistart.marketplaceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    Product findProductById(Long id);

    List<Product> findProductsByUser(User user);
}
