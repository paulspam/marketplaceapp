package com.intellias.intellistart.marketplaceapp.service;

import com.intellias.intellistart.marketplaceapp.exception.InsufficientFundsException;
import com.intellias.intellistart.marketplaceapp.exception.RecordNotFoundException;
import com.intellias.intellistart.marketplaceapp.model.Product;
import com.intellias.intellistart.marketplaceapp.model.User;
import com.intellias.intellistart.marketplaceapp.repository.ProductRepository;
import com.intellias.intellistart.marketplaceapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
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

    public List<User> findAllByProducts(Product product) {
        return null;
    };

    public List<Product> findAllProductByUser(Long userId) throws RecordNotFoundException {
        User user = userRepository.getReferenceById(userId);
        if (user == null) {
            throw new RecordNotFoundException(String.format("User with id = "+ userId + "not found", userId));
        }
        return user.getProducts();
    }

    public User buy (Long userId, Long productId) throws RecordNotFoundException, InsufficientFundsException {
        User user = userRepository.getReferenceById(userId);
        Product product = productRepository.getReferenceById(productId);
        if (user == null) {
            throw new RecordNotFoundException(String.format("User with id = "+ userId + "not found", userId));
        }
        if (product == null) {
            throw new RecordNotFoundException("Product with id = " + productId + " not found");
        }
        if (user.getAmount() < product.getPrice()) {
            throw new InsufficientFundsException("User with id = " + userId +
                " insufficient funds for buying product with id = " + productId);
        } else {
            user.setAmount(user.getAmount() - product.getPrice());
            List<Product> newProducts = user.getProducts();
            newProducts.add(product);
//            if (newProducts.containsKey(product)) {
//                Integer quantity = newProducts.get(product);
//                newProducts.put(product, ++quantity);
//                user.setProducts(newProducts);
//            } else {
//                newProducts.put(product,1);
//                user.setProducts(newProducts);
//            }
        }
        return user;
    }
}
