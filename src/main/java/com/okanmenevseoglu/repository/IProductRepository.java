package com.okanmenevseoglu.repository;

import com.okanmenevseoglu.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Okan Menevseoglu on 13.11.2016.
 * This class indicates that the product is a DAO component that uses Spring Data JPA in the persistence layer.
 */
@Repository
public interface IProductRepository extends CrudRepository<Product, Long> {
    Product findOneByTitle(String title);
}
