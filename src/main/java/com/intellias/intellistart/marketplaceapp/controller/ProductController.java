package com.intellias.intellistart.marketplaceapp.controller;

import com.intellias.intellistart.marketplaceapp.exception.RecordNotFoundException;
import com.intellias.intellistart.marketplaceapp.model.Product;
import com.intellias.intellistart.marketplaceapp.model.User;
import com.intellias.intellistart.marketplaceapp.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl userService) {
        this.productService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> findAll() {
        List<Product> allProducts = productService.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {
        Product user = productService.findById(id);
        if (user == null) {
            return new ResponseEntity(String.format("No product with id = %s", id), HttpStatus.BAD_REQUEST);
        } else return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/users")
    public ResponseEntity< List<Product>> findProductsByUser(@PathVariable long id) {
        List<Product> products;
        try {
            products = productService.findProductsByUser(id);
        } catch (RecordNotFoundException e) {
            return new ResponseEntity("No product with id = " + id, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(products);
    }

    @PostMapping()
    public ResponseEntity<Product> save(@RequestBody Product product) {
        if ((product.getId() != null) && (product.getId() !=0)) {
            return new ResponseEntity("Redundant parameter: userId must be null", HttpStatus.BAD_REQUEST);
        }
        Product newProduct = productService.save(product);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping()
    public ResponseEntity<Product> update(@RequestBody Product product) {
        if ((product.getId() == null) || (product.getId() ==0)) {
            return new ResponseEntity("Missing parameter: userId must be not null", HttpStatus.BAD_REQUEST);
        }
        Product newProduct = productService.save(product);
        return ResponseEntity.ok(newProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity("No user with userId = " + id, HttpStatus.BAD_REQUEST);
        } else {
            productService.deleteById(id);
            return ResponseEntity.ok("User with ID = " + id + " was deleted");
        }
    }
}
