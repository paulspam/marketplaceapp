package com.intellias.intellistart.marketplaceapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
//    @Pattern(regexp = "^(-?)(0|([1-9][0-9]*))(\\\\.[0-9]+)?$")
    private Double price;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<User> user;

}
