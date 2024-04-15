package com.milev.WarehouseORM;

import com.milev.WarehouseORM.Warehouse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WarehouseRepository extends CrudRepository<Warehouse, Integer> {
    Warehouse save(Warehouse warehouse);
}

