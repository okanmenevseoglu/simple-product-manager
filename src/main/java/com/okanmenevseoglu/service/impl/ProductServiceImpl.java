package com.okanmenevseoglu.service.impl;

import com.okanmenevseoglu.model.Product;
import com.okanmenevseoglu.repository.IProductRepository;
import com.okanmenevseoglu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Okan Menevseoglu on 13.11.2016.
 * This class is the business layer of the product operations.
 *
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    IProductRepository productRepository;

    /**
     * @return Iterable Product object
     * @should invoke findAll method of product category repository
     * @should return what product repository returns
     */
    @Override
    public Iterable<Product> getProductList() {
        return productRepository.findAll();
    }

    /**
     * @param id of the product
     * @return Product object with the given id
     * @should invoke findOne method of product repository with given id
     * @should return what product repository returns
     */
    @Override
    public Product getProduct(long id) {
        return productRepository.findOne(id);
    }

    /**
     * @param title of the product
     * @return Product object with the given title
     * @should invoke findOneByTitle method of product repository with given title
     * @should return what product repository returns
     */
    @Override
    public Product getProductByTitle(String title) {
        return productRepository.findOneByTitle(title);
    }

    /**
     * @param product to be saved
     * @should invoke save method of product repository
     */
    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }


    /**
     * @param id of the product that will be updated
     * @param product to represent new object
     * @should invoke save method of product repository with given id and new product data
     * @should invoke save method of product repository if the data is not null
     */
    @Override
    public void updateProduct(long id, Product product) {
        Product oldProduct = productRepository.findOne(id);
        if (product.getTitle() != null)
            oldProduct.setTitle(product.getTitle());
        if (product.getDescription() != null)
            oldProduct.setDescription(product.getDescription());
        if (product.getPrice() != 0)
            oldProduct.setPrice(product.getPrice());
        productRepository.save(oldProduct);
    }

    /**
     * @param id of the product category that will be deleted
     * @should invoke delete method of product repository with given id
     */
    @Override
    public void deleteProduct(long id) {
        productRepository.delete(id);
    }
}
