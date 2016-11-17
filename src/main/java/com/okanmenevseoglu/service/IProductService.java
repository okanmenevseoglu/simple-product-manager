package com.okanmenevseoglu.service;

import com.okanmenevseoglu.model.Product;

/**
 * Created by Okan Menevseoglu on 13.11.2016.
 * This class is the abstract representation of the service layer for the product operations.
 */
public interface IProductService {

    Iterable<Product> getProductList();

    Product getProduct(long id);

    Product getProductByTitle(String title);

    void saveProduct(Product product);

    void updateProduct(long id, Product product);

    void deleteProduct(long id);
}
