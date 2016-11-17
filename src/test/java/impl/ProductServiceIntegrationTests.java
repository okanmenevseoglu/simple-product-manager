package impl;

import com.okanmenevseoglu.SimpleProductManagerApplication;
import com.okanmenevseoglu.model.Product;
import com.okanmenevseoglu.repository.IProductRepository;
import com.okanmenevseoglu.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Okan Menevseoglu on 15.11.2016.
 * This class has the integration tests for the product service operations.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SimpleProductManagerApplication.class)
public class ProductServiceIntegrationTests {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private IProductRepository productRepository;

    private Product product1;
    private Product product2;
    private Product product3;

    @Before
    public void setup() {
        productRepository.deleteAll();

        product1 = new Product();
        product1.setTitle("Title1");
        product1.setDescription("Description1");
        product1.setPrice(123);

        product2 = new Product();
        product2.setTitle("Title2");
        product2.setDescription("Description2");
        product2.setPrice(1234);

        product3 = new Product();
        product3.setTitle("Title3");
        product3.setDescription("Description3");
        product3.setPrice(12345);
    }

    /**
     * @verifies findByTitle method returns product by given title
     * @see ProductServiceImpl#getProductByTitle(String)
     */
    @Test
    public void findByTitleShouldReturnProduct() throws Exception {
        this.productService.saveProduct(product1);
        Product validateProduct = this.productRepository.findOneByTitle("Title1");

        assertEquals("Title1", validateProduct.getTitle());
        assertEquals("Description1", validateProduct.getDescription());
        assertEquals(123, validateProduct.getPrice());
    }

    /**
     * @verifies getProductList method returns product list
     * @see ProductServiceImpl#getProductList()
     */
    @Test
    public void getProductList() throws Exception {
        this.productService.saveProduct(product1);
        this.productService.saveProduct(product2);
        this.productService.saveProduct(product3);

        List<Product> productList = (List<Product>) this.productRepository.findAll();
        assertEquals("Title1", productList.get(0).getTitle());
        assertEquals("Description1", productList.get(0).getDescription());
        assertEquals(123, productList.get(0).getPrice());

        assertEquals("Title2", productList.get(1).getTitle());
        assertEquals("Description2", productList.get(1).getDescription());
        assertEquals(1234, productList.get(1).getPrice());

        assertEquals("Title3", productList.get(2).getTitle());
        assertEquals("Description3", productList.get(2).getDescription());
        assertEquals(12345, productList.get(2).getPrice());
    }

    /**
     * @verifies updateProduct method if it updates the product with given new data
     * @see ProductServiceImpl#updateProduct(long, Product)
     */
    @Test
    public void updateProduct() throws Exception {
        this.productRepository.save(product2);
        Product product2edit = new Product();
        product2edit.setTitle("Title 2 Changed");
        product2edit.setPrice(100);
        this.productService.updateProduct(product2.getId(), product2edit);
        product2 = productService.getProduct(4);

        assertEquals("Title 2 Changed", product2.getTitle());
        assertEquals("Description2", product2.getDescription());
        assertEquals(100, product2.getPrice());

    }

    /**
     * @verifies deleteProduct method deletes the product with given id
     * @see ProductServiceImpl#deleteProduct(long)
     */
    @Test
    public void deleteProduct() throws Exception {
        product1 = new Product();
        product1.setTitle("Title1");
        product1.setDescription("Description1");
        product1.setPrice(123);
        productService.saveProduct(product1);
        productService.deleteProduct(5);
        assertEquals(productService.getProduct(5L), null);
    }
}
