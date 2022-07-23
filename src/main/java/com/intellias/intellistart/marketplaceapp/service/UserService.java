package com.intellias.intellistart.marketplaceapp.service;

import com.intellias.intellistart.marketplaceapp.model.Product;
import com.intellias.intellistart.marketplaceapp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User findUserById(Long id);

    List<User> findAll();

    User saveUser(User user);

    void deleteById(Long id);

    List<User> findAllByProducts(Product product);

}
