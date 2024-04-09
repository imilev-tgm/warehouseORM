package com.milev.WarehouseORM;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.warehouse.id = :warehouseId AND p.id = :productId")
    Optional<Product> findProductByIdAndWarehouseId(@Param("productId") Integer productId, @Param("warehouseId") Integer warehouseId);

}
