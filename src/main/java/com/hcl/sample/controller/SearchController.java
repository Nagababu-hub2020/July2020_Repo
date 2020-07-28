package com.hcl.sample.controller;

import com.hcl.sample.model.Manufacturer;
import com.hcl.sample.model.Product;
import com.hcl.sample.model.ProductCategory;
import com.hcl.sample.service.ProductCategoryService;
import com.hcl.sample.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private Logger logger = (Logger) LoggerFactory.getLogger(SearchController.class);

    @Autowired
    SearchService searchService;

    @GetMapping("/products/{productName}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String productName){
     List<Product> existingProducts =searchService.searchProductsByName(productName);


     if(existingProducts!=null && existingProducts.size()>0){
            return ResponseEntity.status(HttpStatus.OK)
                    .body( existingProducts);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<Product>());
        }

    }

    @GetMapping("/manufacturers/{manufacturerRegion}")
    public ResponseEntity<List<Product>> getProductByRegion(@PathVariable String manufacturerRegion){
        List<Product> products =searchService.searchProductsByRegion(manufacturerRegion);
        if(products!=null && products.size()>0){
            return ResponseEntity.status(HttpStatus.OK)
                    .body( products);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<Product>());
        }

    }


}
