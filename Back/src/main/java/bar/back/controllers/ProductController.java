package bar.back.controllers;

import bar.back.dtos.ProductDto;
import bar.back.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController{
    @Autowired
    private ProductService productService;

    @PostMapping("product/Create")
    public ResponseEntity<ProductDto> CreateProduct(@RequestBody ProductDto dto){
        return  ResponseEntity.ok(productService.createProduct(dto));
    }

    @PutMapping("product/Edit")
    public ResponseEntity<ProductDto> editProduct(@RequestBody ProductDto dto){
        return  ResponseEntity.ok(productService.editProduct(dto));
    }

    @DeleteMapping("product/delete/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id) throws Exception {
        return  ResponseEntity.ok(productService.deleteProduct(id));
    }
    @GetMapping("product/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) throws Exception {
        return  ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("product/")
    public ResponseEntity<List<ProductDto>> getProducts() throws Exception {
        return  ResponseEntity.ok(productService.getProducts());
    }
}
