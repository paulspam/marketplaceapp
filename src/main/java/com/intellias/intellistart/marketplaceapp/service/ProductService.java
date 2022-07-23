package com.intellias.intellistart.marketplaceapp.service;

import com.intellias.intellistart.marketplaceapp.model.Product;
import com.intellias.intellistart.marketplaceapp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    List<Product> findProductsByUser(User user);
}
