package com.intellias.intellistart.marketplaceapp.service;

import java.util.List;
import java.util.Set;

import com.intellias.intellistart.marketplaceapp.exception.RecordNotFoundException;
import com.intellias.intellistart.marketplaceapp.model.Product;
import com.intellias.intellistart.marketplaceapp.model.User;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    Set<User> findUsersByProduct(Long productId) throws RecordNotFoundException;
}
