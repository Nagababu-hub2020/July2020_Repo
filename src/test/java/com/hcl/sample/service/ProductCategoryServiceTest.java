package com.hcl.sample.service;

import com.hcl.sample.SampleAppApplication;
import com.hcl.sample.Repository.ProductCategoryRepository;
import com.hcl.sample.model.ProductCategory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;

@WebMvcTest(SampleAppApplication.class)
public class ProductCategoryServiceTest {

    @InjectMocks
    private ProductCategoryService productCategoryService;

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addProductCategoryTest(){
        ProductCategory productCategory = getProductCategory();

        Mockito.when(productCategoryRepository.saveAndFlush(Mockito.any(ProductCategory.class))).thenReturn(productCategory);

        ProductCategory productCategory1 = productCategoryService.createProductCategory(productCategory);

        Assert.assertNotNull(productCategory1);
        Assert.assertEquals(productCategory1.getProductCategoryName(), productCategory.getProductCategoryName());
    }


    @Test
    public void getProductCategoryByIdTest(){
        ProductCategory productCategory = getProductCategory();

        Mockito.when(productCategoryRepository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(productCategory));

        ProductCategory productCategory1 = productCategoryService.getProductCategoryById(productCategory.getProductCategoryId());
        Assert.assertNotNull(productCategory1);
        Assert.assertEquals(productCategory1.getProductCategoryName(), productCategory.getProductCategoryName());
    }

    @Test
    public void deleteProductCategoryTest(){
        ProductCategory productCategory = getProductCategory();

        Mockito.doNothing().when(productCategoryRepository).delete(Mockito.any(ProductCategory.class));

        productCategoryService.deleteProductCategory(productCategory);
        Mockito.verify(productCategoryRepository, Mockito.times(1)).delete(productCategory);
    }


    @Test
    public void updateProductCategoryTest(){
        ProductCategory productCategory = getProductCategory();

        Mockito.when(productCategoryRepository.save(Mockito.any(ProductCategory.class))).thenReturn(productCategory);

        ProductCategory productCategory1 =  productCategoryService.updateProductCategory(productCategory);

        Assert.assertNotNull(productCategory1);
        Assert.assertEquals(productCategory1.getProductCategoryName(), productCategory.getProductCategoryName());

    }

    private ProductCategory getProductCategory(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("HCL");
        productCategory.setProductCategoryId(1);
        productCategory.setProducts(new ArrayList<>());
        return productCategory;
    }

}
