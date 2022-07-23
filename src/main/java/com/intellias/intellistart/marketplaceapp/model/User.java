package com.intellias.intellistart.marketplaceapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Pattern(regexp = "^(-?)(0|([1-9][0-9]*))(\\\\.[0-9]+)?$")
    private Double amount;

    @OneToMany(mappedBy = "user")
    private List<Product> products;


}
