package com.intellias.intellistart.marketplaceapp.service;

import java.util.List;

import com.intellias.intellistart.marketplaceapp.exception.RecordNotFoundException;
import com.intellias.intellistart.marketplaceapp.model.Product;
import com.intellias.intellistart.marketplaceapp.model.User;

public interface UserService {

    User findUserById(Long id) throws RecordNotFoundException;

    List<User> findAll();

    User saveUser(User user);

    void deleteById(Long id);

    List<Product> findAllProductByUser(Long id) throws RecordNotFoundException;
}
