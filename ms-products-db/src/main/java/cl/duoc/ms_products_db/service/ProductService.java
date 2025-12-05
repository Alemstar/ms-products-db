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
        productDTO.setCode(product.getCode());
        productDTO.setProductName(product.getProductName());
        productDTO.setDescripcion(product.getDescripcion());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());
        productDTO.setCategoria(product.getCategoria());
        productDTO.setImagen(product.getImagen());
        productDTO.setPersonalizable(product.isPersonalizable());
        productDTO.setMaxMsgChars(product.getMaxMsgChars());
        productDTO.setTipoForma(product.getTipoForma());
        productDTO.setTamanosDisponibles(product.getTamanosDisponibles());

        return productDTO;
    }

    public ProductDTO getProductById(String code){

        Optional<Product> product = productRepository.findById(code);

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
            productDTO.setCode(prod.getCode());
            productDTO.setProductName(prod.getProductName());
            productDTO.setDescripcion(prod.getDescripcion());
            productDTO.setPrice(prod.getPrice());
            productDTO.setStock(prod.getStock());
            productDTO.setCategoria(prod.getCategoria());
            productDTO.setImagen(prod.getImagen());
            productDTO.setPersonalizable(prod.isPersonalizable());
            productDTO.setMaxMsgChars(prod.getMaxMsgChars());
            productDTO.setTipoForma(prod.getTipoForma());
            productDTO.setTamanosDisponibles(prod.getTamanosDisponibles());

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
        product.setCode(productDTO.getCode());
        product.setProductName(productDTO.getProductName());
        product.setDescripcion(productDTO.getDescripcion());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setCategoria(productDTO.getCategoria());
        product.setImagen(productDTO.getImagen());
        product.setPersonalizable(productDTO.isPersonalizable());
        product.setMaxMsgChars(productDTO.getMaxMsgChars());
        product.setTipoForma(productDTO.getTipoForma());
        product.setTamanosDisponibles(productDTO.getTamanosDisponibles());

        return product;
    }

    public ResponseEntity<String> insertProduct(ProductDTO productDTO){

        // Verificar si el código ya existe
        if (productDTO.getCode() == null || productDTO.getCode().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product code is required.");
        }

        Optional<Product> existingProduct = productRepository.findById(productDTO.getCode());
        
        if (existingProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The product code already exists.");
        }

        else{
            Product newProduct = translateDtoToEntity(productDTO);
            productRepository.save(newProduct);

            return ResponseEntity.ok("Product created.");
        }

    }

    public ResponseEntity<String> deleteProduct(String code){
        Optional<Product> product = productRepository.findById(code);
        if(product.isPresent()){
        productRepository.deleteById(code);
        return ResponseEntity.ok("Product with code: " + code + " was deleted.");}
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The product with code: " + code + " doesn't exist.");
        }
    }

    public ResponseEntity<String> updateProduct(ProductDTO productDTO){
        Optional<Product> productCode = productRepository.findById(productDTO.getCode());
        Optional<Product> productName = productRepository.findByProductName(productDTO.getProductName());

        if(!productCode.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The product cannot be updated because it doesn't exist.");
        }

        Product updateProduct = productCode.get();

        if (productCode.isPresent() && productName.isPresent()){
                updateProduct.setPrice(productDTO.getPrice());
                updateProduct.setStock(productDTO.getStock());
                productRepository.save(updateProduct);
                return ResponseEntity.status(HttpStatus.CONFLICT).body("This product Name and product code already exists, updated data: \n"
                                                                                                                + "Price: "
                                                                                                                + updateProduct.getPrice()
                                                                                                                +"\n"
                                                                                                                +"Stock:"
                                                                                                                +updateProduct.getStock());}
        
        else if (productCode.isPresent()){
            updateProduct.setProductName(productDTO.getProductName());
            updateProduct.setPrice(productDTO.getPrice());
            updateProduct.setStock(productDTO.getStock());
            productRepository.save(updateProduct);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This product code does already exist, update data: \n"
                                                                                                                + "Product name: "
                                                                                                                + updateProduct.getProductName()
                                                                                                                + "\n"
                                                                                                                + "Price: "
                                                                                                                + updateProduct.getPrice()
                                                                                                                +"\n"
                                                                                                                +"Stock:"
                                                                                                                +updateProduct.getStock());}
        
        else if (productName.isPresent()){
            updateProduct.setCode(productDTO.getCode());
            updateProduct.setPrice(productDTO.getPrice());
            updateProduct.setStock(productDTO.getStock());
            productRepository.save(updateProduct);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This product Name does already exist, updated data: \n"
                                                                                                                + "Product code: "
                                                                                                                + updateProduct.getCode()
                                                                                                                + "\n"
                                                                                                                + "Price: "
                                                                                                                + updateProduct.getPrice()
                                                                                                                +"\n"
                                                                                                                +"Stock:"
                                                                                                                +updateProduct.getStock());}
        
        else {
            updateProduct.setCode(productDTO.getCode());
            updateProduct.setProductName(productDTO.getProductName());
            updateProduct.setPrice(productDTO.getPrice());
            updateProduct.setStock(productDTO.getStock());
            productRepository.save(updateProduct);
            return ResponseEntity.ok("Product updated: \n"
                                            + "Product code: "
                                            + updateProduct.getCode()
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