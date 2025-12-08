package cl.duoc.ms_products_db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ms_products_db.model.dto.ProductDTO;
import cl.duoc.ms_products_db.service.ProductService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public List<ProductDTO> selectAllProduct(){
        return productService.selectAllProduct();
    }

    @GetMapping("/{code}")
    public ProductDTO getProductByCode(@PathVariable("code") String code){
        return productService.getProductById(code);
    }

    @GetMapping("/GetProductByCode/{code}")
    public ProductDTO getProductByCodeLegacy(@PathVariable("code") String code){
        return productService.getProductById(code);
    }

    @PostMapping("/insertProduct")
    public ResponseEntity<String> insertProduct(@RequestBody ProductDTO productDTO){
        return productService.insertProduct(productDTO);
    }

    @DeleteMapping("/DeleteProductByCode/{code}")
    public ResponseEntity<String> deleteProduct(@PathVariable("code") String code){
        return productService.deleteProduct(code);
    }

    @PutMapping("/UpdateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO){
        return productService.updateProduct(productDTO);
    }

}
