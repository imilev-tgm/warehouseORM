package com.milev.WarehouseORM;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping(path="/addWarehouse")
    public @ResponseBody String addNewWarehouse (@RequestParam String warehouseName,
                                                 @RequestParam String warehouseCountry,
                                                 @RequestParam String warehouseCity,
                                                 @RequestParam String address) {

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName(warehouseName);
        warehouse.setWarehouseCountry(warehouseCountry);
        warehouse.setWarehouseCity(warehouseCity);
        warehouse.setAddress(address);
        warehouseRepository.save(warehouse);
        return "Warehouse Saved";
    }

    @PostMapping(path="/addProduct")
    public @ResponseBody String addNewProduct (@RequestParam String productName,
                                               @RequestParam String productCategory,
                                               @RequestParam int productAmount,
                                               @RequestParam int warehouseId) {

        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);
        if (warehouse != null) {
            Product product = new Product();
            product.setProductName(productName);
            product.setProductCategory(productCategory);
            product.setProductAmount(productAmount);
            product.setWarehouse(warehouse);
            productRepository.save(product);
            return "Product Saved";
        } else {
            return "Warehouse not found";
        }
    }

    @GetMapping(path="/allWarehouses")
    public @ResponseBody Iterable<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @GetMapping(path="/allProducts")
    public @ResponseBody Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping(path="/warehouse/{id}")
    public @ResponseBody Warehouse getWarehouseById(@PathVariable int id) {
        return warehouseRepository.findById(id).orElse(null);
    }


    @GetMapping(path="/warehouse/{warehouseId}/product/{productId}")
    public @ResponseBody Product getProductById(@PathVariable int warehouseId, @PathVariable int productId) {
        // Call the custom repository method to find the product by warehouseId and productId
        return productRepository.findProductByIdAndWarehouseId(productId, warehouseId).orElse(null); // Return null if product not found
    }



    @PutMapping(path="/warehouse/{id}")
    public @ResponseBody String updateWarehouse(@PathVariable int id, @RequestBody Warehouse updatedWarehouse) {
        Warehouse warehouse = warehouseRepository.findById(id).orElse(null);
        if (warehouse != null) {
            // Update properties of warehouse
            warehouse.setWarehouseName(updatedWarehouse.getWarehouseName());
            warehouse.setWarehouseCountry(updatedWarehouse.getWarehouseCountry());
            warehouse.setWarehouseCity(updatedWarehouse.getWarehouseCity());
            warehouse.setAddress(updatedWarehouse.getAddress());
            // Save the updated warehouse
            warehouseRepository.save(warehouse);
            return "Warehouse updated";
        } else {
            return "Warehouse not found";
        }
    }


}
