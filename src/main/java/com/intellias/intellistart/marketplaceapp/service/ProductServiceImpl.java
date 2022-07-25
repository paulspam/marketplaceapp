package com.intellias.intellistart.marketplaceapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intellias.intellistart.marketplaceapp.exception.RecordNotFoundException;
import com.intellias.intellistart.marketplaceapp.model.Product;
import com.intellias.intellistart.marketplaceapp.model.User;
import com.intellias.intellistart.marketplaceapp.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Set<User> findUsersByProduct(Long productId) throws RecordNotFoundException {
        Product product = productRepository.findProductById(productId);
        if (product == null) {
            throw new RecordNotFoundException("Product with id = "+ productId + "not found");
        }
        List<User> userList = product.getUser();
        Set<User> userSet = new HashSet<>();
        userSet.addAll(userList);
        return userSet;
    }
}
