package com.intellias.intellistart.marketplaceapp.controller;

import com.intellias.intellistart.marketplaceapp.exception.InsufficientFundsException;
import com.intellias.intellistart.marketplaceapp.exception.RecordNotFoundException;
import com.intellias.intellistart.marketplaceapp.model.Product;
import com.intellias.intellistart.marketplaceapp.model.User;
import com.intellias.intellistart.marketplaceapp.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
//@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//@RequestMapping(value = "/users", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
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
    public ResponseEntity<User> findById(@PathVariable long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return new ResponseEntity("No user with userId = " + id, HttpStatus.BAD_REQUEST);
        } else return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> findAllProductByUser(@PathVariable long id) throws RecordNotFoundException {
        List<Product> products = userService.findAllProductByUser(id);
        return ResponseEntity.ok(products);
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping()
    public ResponseEntity<User> saveNewUser(@RequestBody User user) {
        if ((user.getId() != null) && (user.getId() !=0)) {
            return new ResponseEntity("Redundant parameter: userId must be null", HttpStatus.BAD_REQUEST);
        }
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if ((user.getId() == null) || (user.getId() ==0)) {
            return new ResponseEntity("Missing parameter: userId must be not null", HttpStatus.BAD_REQUEST);
        }
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return new ResponseEntity("No user with userId = " + id, HttpStatus.BAD_REQUEST);
        } else {
            userService.deleteById(id);
            return ResponseEntity.ok("User with ID = " + id + " was deleted");
        }
    }

    @PostMapping("/{userId}/buy/{productId}")
    public ResponseEntity<User> buyProduct(@PathVariable long userId, @PathVariable long productId)
        throws RecordNotFoundException, InsufficientFundsException {
        User newUser = userService.buy(userId, productId);
        return ResponseEntity.ok(newUser);
    }
}
