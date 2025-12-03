package cl.duoc.ms_products_db.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ProductDTO {
    @JsonProperty(value = "product_id")
    private String idProduct;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "nombre")
    private String productName;

    @JsonProperty(value = "descripcion")
    private String descripcion;

    @JsonProperty(value = "precio")
    private int price;

    @JsonProperty(value = "stock")
    private int stock;

    @JsonProperty(value = "categoriaId")
    private String categoriaId;

    @JsonProperty(value = "imagen")
    private String imagen;

    @JsonProperty(value = "personalizable")
    private boolean personalizable;

    @JsonProperty(value = "maxMsgChars")
    private int maxMsgChars;

    @JsonProperty(value = "tipoForma")
    private String tipoForma;

    @JsonProperty(value = "tamanosDisponibles")
    private String tamanosDisponibles;
}
