package impl;

import com.okanmenevseoglu.model.Product;
import com.okanmenevseoglu.repository.IProductRepository;
import com.okanmenevseoglu.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Okan Menevseoglu on 14.11.2016.
 * This class has the unit tests for the product service operations.
 */
public class ProductServiceImplUnitTests {

    @Mock
    IProductRepository productRepository;

    @Mock
    private ArrayList<Product> productList;

    @Mock
    private Product product;

    @InjectMocks
    ProductServiceImpl productService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * @verifies invoke findAll method of product category repository
     * @see ProductServiceImpl#getProductList()
     */
    @Test
    public void getProductList_shouldInvokeFindAllMethodOfProductCategoryRepository() throws Exception {
        productService.getProductList();
        verify(productRepository, times(1)).findAll();
    }

    /**
     * @verifies return what product repository returns
     * @see ProductServiceImpl#getProductList()
     */
    @Test
    public void getProductList_shouldReturnWhatProductRepositoryReturns() throws Exception {
        when(productRepository.findAll()).thenReturn(productList);
        assertEquals(productList, productService.getProductList());
    }

    /**
     * @verifies invoke findOne method of product repository with given id
     * @see ProductServiceImpl#getProduct(long)
     */
    @Test
    public void getProduct_shouldInvokeFindOneMethodOfProductRepositoryWithGivenId() throws Exception {
        productService.getProduct(anyLong());
        verify(productRepository, times(1)).findOne(anyLong());
    }

    /**
     * @verifies return what product repository returns
     * @see ProductServiceImpl#getProduct(long)
     */
    @Test
    public void getProduct_shouldReturnWhatProductRepositoryReturns() throws Exception {
        when(productRepository.findOne(anyLong())).thenReturn(product);
        assertEquals(product, productService.getProduct(anyLong()));
    }

    /**
     * @verifies invoke findOneByTitle method of product repository with given title
     * @see ProductServiceImpl#getProductByTitle(String)
     */
    @Test
    public void getProductByTitle_shouldInvokeFindOneByTitleMethodOfProductRepositoryWithGivenTitle() throws Exception {
        productService.getProductByTitle(anyString());
        verify(productRepository, times(1)).findOneByTitle(anyString());
    }

    /**
     * @verifies return what product repository returns
     * @see ProductServiceImpl#getProductByTitle(String)
     */
    @Test
    public void getProductByTitle_shouldReturnWhatProductRepositoryReturns() throws Exception {
        when(productRepository.findOneByTitle(anyString())).thenReturn(product);
        assertEquals(product, productService.getProductByTitle(anyString()));
    }

    /**
     * @verifies invoke save method of product repository
     * @see ProductServiceImpl#saveProduct(com.okanmenevseoglu.model.Product)
     */
    @Test
    public void saveProduct_shouldInvokeSaveMethodOfProductRepository() throws Exception {
        productService.saveProduct(product);
        verify(productRepository, times(1)).save(product);
    }

    /**
     * @verifies invoke save method of product repository with given id and new product data
     * @see ProductServiceImpl#updateProduct(long, com.okanmenevseoglu.model.Product)
     */
    @Test
    public void updateProduct_shouldInvokeSaveMethodOfProductRepositoryWithGivenIdAndNewProductData() throws Exception {
        productService.updateProduct(anyLong(), product);
        verify(productRepository, times(1)).findOne(anyLong());
        verify(productRepository, times(1)).save(productRepository.findOne(anyLong()));
    }

    /**
     * @verifies invoke save method of product repository if the data is not null
     * @see ProductServiceImpl#updateProduct(long, com.okanmenevseoglu.model.Product)
     */
    @Test
    public void updateProduct_shouldInvokeSaveMethodOfProductRepositoryIfTheDataIsNotNull() throws Exception {
        product.setTitle(anyString());
        productService.updateProduct(anyLong(), product);
        product.setDescription(anyString());
        productService.updateProduct(anyLong(), product);
        product.setPrice(anyInt());
        productService.updateProduct(anyLong(), product);
        verify(productRepository, times(3)).findOne(anyLong());
        verify(productRepository, times(3)).save(productRepository.findOne(anyLong()));
    }

    /**
     * @verifies invoke delete method of product repository with given id
     * @see ProductServiceImpl#deleteProduct(long)
     */
    @Test
    public void deleteProduct_shouldInvokeDeleteMethodOfProductRepositoryWithGivenId() throws Exception {
        productService.deleteProduct(anyLong());
        verify(productRepository, times(1)).delete(anyLong());
    }
}
