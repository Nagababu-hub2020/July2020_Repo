package com.hcl.sample.Repository;

import com.hcl.sample.model.Manufacturer;
import com.hcl.sample.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
    List<Manufacturer> findByProductProductId(int productId);
    List<Manufacturer> findByManufacturerRegion(String manufacturerRegion);
}
