package cl.duoc.ms_products_db.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cl.duoc.ms_products_db.model.dto.ProductDTO;
import cl.duoc.ms_products_db.model.entities.Product;
import cl.duoc.ms_products_db.model.repository.ProductRepository;


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductDTO translateEntityToDTO(Product product){

        ProductDTO productDTO = new ProductDTO();
        productDTO.setIdProduct(product.getIdProduct());
        productDTO.setProductName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());

        return productDTO;
    }

    public ProductDTO getProductById(Long idProduct){

        Optional<Product> product = productRepository.findById(idProduct);

        ProductDTO productDTO = null;

        if(product.isPresent())
            return productDTO = translateEntityToDTO(product.get());
        else
            return productDTO;
    }

    public List<ProductDTO> translateListEntityToDTO(List<Product> product){

        List<ProductDTO> listDTO = new ArrayList<>();
        ProductDTO productDTO = null;
        for (Product prod: product){
            productDTO = new ProductDTO();
            productDTO.setIdProduct(prod.getIdProduct());
            productDTO.setProductName(prod.getProductName());
            productDTO.setPrice(prod.getPrice());
            productDTO.setStock(prod.getStock());

            listDTO.add(productDTO);
        }
        return listDTO; 
    }

    public List<ProductDTO> selectAllProduct(){

        List<Product> productsList = productRepository.findAll();
        List<ProductDTO> productsListDTO = translateListEntityToDTO(productsList);
        return productsListDTO;
    }
    
    public Product translateDtoToEntity(ProductDTO productDTO){
        Product product = new Product();
        product.setIdProduct(productDTO.getIdProduct());
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

        return product;
    }

    public ResponseEntity<String> insertProduct(ProductDTO productDTO){

        Optional<Product> productName = productRepository.findByProductName(productDTO.getProductName());

        
        if (productName.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The product Name does already exists.");
        }

        else{
            Product newProduct = translateDtoToEntity(productDTO);
            productRepository.save(newProduct);

            return ResponseEntity.ok("Product created.");
        }

    }

    public ResponseEntity<String> deleteProduct(Long idProduct){
        Optional<Product> product = productRepository.findById(idProduct);
        if(product.isPresent()){
        productRepository.deleteById(idProduct);
        return ResponseEntity.ok("Product with ID: " + idProduct + "was deleted.");}
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer with ID: " + idProduct + " doesn't exist.");
        }
    }

    public ResponseEntity<String> updateProduct(ProductDTO productDTO){
        Optional<Product> productId = productRepository.findById(productDTO.getIdProduct());
        Optional<Product> productName = productRepository.findByProductName(productDTO.getProductName());

        if(!productId.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The product cannot be updated because it doesn't exist.");
        }

        Product updateProduct = productId.get();

        if (productId.isPresent() && productName.isPresent()){
                updateProduct.setPrice(productDTO.getPrice());
                updateProduct.setStock(productDTO.getStock());
                productRepository.save(updateProduct);
                return ResponseEntity.status(HttpStatus.CONFLICT).body("This product Name and product Id already exists, updated data: \n"
                                                                                                                + "Price: "
                                                                                                                + updateProduct.getPrice()
                                                                                                                +"\n"
                                                                                                                +"Stock:"
                                                                                                                +updateProduct.getStock());}
        
        else if (productId.isPresent()){
            updateProduct.setProductName(productDTO.getProductName());
            updateProduct.setPrice(productDTO.getPrice());
            updateProduct.setStock(productDTO.getStock());
            productRepository.save(updateProduct);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This product Id does already exist, update data: \n"
                                                                                                                + "Product name: "
                                                                                                                + updateProduct.getProductName()
                                                                                                                + "\n"
                                                                                                                + "Price: "
                                                                                                                + updateProduct.getPrice()
                                                                                                                +"\n"
                                                                                                                +"Stock:"
                                                                                                                +updateProduct.getStock());}
        
        else if (productName.isPresent()){
            updateProduct.setIdProduct(productDTO.getIdProduct());
            updateProduct.setPrice(productDTO.getPrice());
            updateProduct.setStock(productDTO.getStock());
            productRepository.save(updateProduct);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This product Name does already exist, updated data: \n"
                                                                                                                + "Product Id: "
                                                                                                                + updateProduct.getIdProduct()
                                                                                                                + "\n"
                                                                                                                + "Price: "
                                                                                                                + updateProduct.getPrice()
                                                                                                                +"\n"
                                                                                                                +"Stock:"
                                                                                                                +updateProduct.getStock());}
        
        else {
            updateProduct.setIdProduct(productDTO.getIdProduct());
            updateProduct.setProductName(productDTO.getProductName());
            updateProduct.setPrice(productDTO.getPrice());
            updateProduct.setStock(productDTO.getStock());
            productRepository.save(updateProduct);
            return ResponseEntity.ok("Product updated: \n"
                                            + "Product Id: "
                                            + updateProduct.getIdProduct()
                                            + "\n"
                                            + "Product Name: "
                                            + updateProduct.getProductName()
                                            + "\n"
                                            + "Price: "
                                            + updateProduct.getPrice()
                                            +"\n"
                                            +"Stock:"
                                            +updateProduct.getStock());}
                                                                                                                  
    }
}