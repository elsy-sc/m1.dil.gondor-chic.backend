package com.gondor.magic_vente_stock.metierServices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(
        name = "t_produit"
)
public class Produit {
    @Id
    private String id = "";
    private String libelle;
    private String photo;
    private String reference;
    @Column(name = "estdujour")
    private Boolean estDuJour;
    private Float prix;
    @Column(name = "quantiteenstock")
    private Integer quantiteEnStock;
}
