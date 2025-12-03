package cl.duoc.ms_products_db.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.ms_products_db.model.entities.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    
    Optional<Product> findByProductName (String productName);
}
