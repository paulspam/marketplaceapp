package com.intellias.intellistart.marketplaceapp.service;

import java.util.List;

import com.intellias.intellistart.marketplaceapp.exception.InsufficientFundsException;
import com.intellias.intellistart.marketplaceapp.exception.RecordNotFoundException;
import com.intellias.intellistart.marketplaceapp.model.Product;
import com.intellias.intellistart.marketplaceapp.model.User;
import com.intellias.intellistart.marketplaceapp.repository.ProductRepository;
import com.intellias.intellistart.marketplaceapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public User findUserById(Long id) throws RecordNotFoundException {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new RecordNotFoundException("User with id = "+ id + " not found");
        }
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<Product> findAllProductByUser(Long userId) throws RecordNotFoundException {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new RecordNotFoundException("User with id = "+ userId + " not found");
        }
        return user.getProducts();
    }

    public User buy (Long userId, Long productId) throws RecordNotFoundException, InsufficientFundsException {
        User user = userRepository.findUserById(userId);
        Product product = productRepository.findProductById(productId);
        if (user == null) {
            throw new RecordNotFoundException("User with id = "+ userId + " not found");
        }
        if (product == null) {
            throw new RecordNotFoundException("Product with id = " + productId + " not found");
        }
        if (user.getAmount().compareTo(product.getPrice()) < 0) {
            throw new InsufficientFundsException("User with id = " + userId +
                " insufficient funds for buying product with id = " + productId);
        } else {
            user.setAmount(user.getAmount().subtract(product.getPrice()));
            List<Product> newProducts = user.getProducts();
            newProducts.add(product);
            user.setProducts(newProducts);
            userRepository.save(user);
        }
        return user;
    }
}
