package cl.duoc.ms_products_db.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString

public class Product {

    @Id
    @Column(name = "product_id")
    private String idProduct;
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "product_name")
    private String productName;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "price")
    private int price;
    
    @Column(name = "stock")
    private int stock;
    
    @Column(name = "categoria_id")
    private String categoriaId;
    
    @Column(name = "imagen")
    private String imagen;
    
    @Column(name = "personalizable")
    private boolean personalizable;
    
    @Column(name = "max_msg_chars")
    private int maxMsgChars;
    
    @Column(name = "tipo_forma")
    private String tipoForma;
    
    @Column(name = "tamanos_disponibles")
    private String tamanosDisponibles;
    
}
