package com.tmk.ecom.product_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    // TODO: add image for brand


    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Product> products;

}
