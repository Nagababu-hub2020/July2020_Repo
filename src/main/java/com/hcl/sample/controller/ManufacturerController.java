package com.hcl.sample.controller;

import com.hcl.sample.model.Manufacturer;
import com.hcl.sample.model.Product;
import com.hcl.sample.service.ManufacturerService;
import com.hcl.sample.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ManufacturerController {

    private Logger logger = (Logger) LoggerFactory.getLogger(ManufacturerController.class);

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    ProductService productService;

    @GetMapping("products/{productId}/manufacturers")
    public ResponseEntity<List<Manufacturer>> getManufacturers(int productId) {
        List<Manufacturer> manufacturers =manufacturerService. getManufacturerByProductId(productId);
        if (manufacturers != null && manufacturers.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(manufacturers);
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<Manufacturer>());
        }

    }

    @GetMapping("/manufacturers/{manufacturerId}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable  Integer manufacturerId) {

        Manufacturer manufacturer = manufacturerService.getManufacturerById(manufacturerId);

        if (manufacturer != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(manufacturer);
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(null);
        }
    }

    @PostMapping("products/{productId}/manufacturers")
    public ResponseEntity<Manufacturer> addManufacturer(@RequestBody Manufacturer manufacturer,@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        manufacturer.setProduct(product);

        Manufacturer createdManufacturer = manufacturerService.createManufacturer(manufacturer);

        if (createdManufacturer != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(createdManufacturer);
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(null);
        }
    }


    @PutMapping("/manufacturers")
    public ResponseEntity<Manufacturer> updateManufacturer(@RequestBody Manufacturer manufacturer ) {

        Manufacturer updatedManufacturer = manufacturerService.updateManufacturer( manufacturer);

        if (updatedManufacturer != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(updatedManufacturer);
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(null);
        }
    }


    @DeleteMapping("/manufacturers")
    public ResponseEntity<String> deleteProduct(@RequestBody Manufacturer manufacturer) {

        manufacturerService.deleteManufacturer(manufacturer);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(" Manufacturer deleted successfully");
    }

    }
