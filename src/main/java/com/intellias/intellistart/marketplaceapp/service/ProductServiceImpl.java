package com.intellias.intellistart.marketplaceapp.service;

import com.intellias.intellistart.marketplaceapp.exception.RecordNotFoundException;
import com.intellias.intellistart.marketplaceapp.model.Product;
import com.intellias.intellistart.marketplaceapp.model.User;
import com.intellias.intellistart.marketplaceapp.repository.ProductRepository;
import com.intellias.intellistart.marketplaceapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
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

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductsByUser(Long userId) throws RecordNotFoundException {
        User user = userRepository.getReferenceById(userId);
        if (user == null) {
            throw new RecordNotFoundException(String.format("User with id = "+ userId + "not found", userId));
        }
        return productRepository.findProductsByUser(user);
    }
}
