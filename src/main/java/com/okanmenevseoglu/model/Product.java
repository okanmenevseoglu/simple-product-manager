package com.okanmenevseoglu.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Okan Menevseoglu on 13.11.2016.
 * Entity representation of the product table that stores information about products.
 */
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private int price;
}
