package com.okanmenevseoglu.controller;

import com.okanmenevseoglu.model.Product;
import com.okanmenevseoglu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Okan Menevseoglu on 13.11.2016.
 * Controller class that handles request and response methods of the product operations.
 */

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    IProductService productService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public Iterable<Product> getProductList() {
        return productService.getProductList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("id") long id) {
        return productService.getProduct(id);
    }

    @RequestMapping(value = "/byTitle/{title}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("title") String title) {
        return productService.getProductByTitle(title);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
    }
}
