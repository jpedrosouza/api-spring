package com.study.apispring.controllers;

import com.study.apispring.models.ProductModel;
import com.study.apispring.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public @ResponseBody String addNewProduct(@RequestParam String name, String description, double price,
            long quantity) {
        ProductModel productModel = new ProductModel();

        productModel.setName(name);
        productModel.setDescription(description);
        productModel.setPrice(price);
        productModel.setQuantity(quantity);

        productRepository.save(productModel);

        return "Saved!";
    }

    @GetMapping("/products")
    public @ResponseBody Iterable<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable("id") Integer id,
            @RequestBody ProductModel productModel) {

        return productRepository.findById(id).map(record -> {
            record.setName(productModel.getName());
            record.setDescription(productModel.getDescription());
            record.setPrice(productModel.getPrice());
            record.setQuantity(productModel.getQuantity());
            ProductModel updated = productRepository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/delete")
    public @ResponseBody String removeProduct(@RequestParam Integer id) {
        productRepository.deleteById(id);

        return "Deleted!";
    }
}
