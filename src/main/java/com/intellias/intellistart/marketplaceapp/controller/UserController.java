package com.intellias.intellistart.marketplaceapp.controller;

import java.util.List;

import com.intellias.intellistart.marketplaceapp.exception.InsufficientFundsException;
import com.intellias.intellistart.marketplaceapp.exception.RecordNotFoundException;
import com.intellias.intellistart.marketplaceapp.model.Product;
import com.intellias.intellistart.marketplaceapp.model.User;
import com.intellias.intellistart.marketplaceapp.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> findAll() {
        List<User> allUsers = userService.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable long id) throws RecordNotFoundException {
        User user = userService.findUserById(id);
        if (user == null) {
            return new ResponseEntity("No user with id = " + id, HttpStatus.BAD_REQUEST);
        } else return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> findAllProductByUser(@PathVariable long id) throws RecordNotFoundException {
        List<Product> products = userService.findAllProductByUser(id);
        return ResponseEntity.ok(products);
    }

    @PostMapping()
    public ResponseEntity<User> saveNewUser(@RequestBody User user) {
        if ((user.getId() != null) && (user.getId() !=0)) {
            return new ResponseEntity("Redundant parameter: id must be null", HttpStatus.BAD_REQUEST);
        }
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if ((user.getId() == null) || (user.getId() ==0)) {
            return new ResponseEntity("Missing parameter: id must be not null", HttpStatus.BAD_REQUEST);
        }
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) throws RecordNotFoundException {
        User user = userService.findUserById(id);
        if (user == null) {
            return new ResponseEntity("No user with id = " + id, HttpStatus.BAD_REQUEST);
        } else {
            userService.deleteById(id);
            return ResponseEntity.ok("User with id = " + id + " was deleted");
        }
    }

    @PostMapping("/{userId}/buy/{productId}")
    public ResponseEntity<User> buyProduct(@PathVariable long userId, @PathVariable long productId)
        throws RecordNotFoundException, InsufficientFundsException {
        User newUser = userService.buy(userId, productId);
        return ResponseEntity.ok(newUser);
    }
}
