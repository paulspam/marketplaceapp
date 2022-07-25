package com.intellias.intellistart.marketplaceapp.repository;

import java.util.List;

import com.intellias.intellistart.marketplaceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    User findUserById(Long id);
}
