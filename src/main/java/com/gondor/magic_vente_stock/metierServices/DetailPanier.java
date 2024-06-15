package com.gondor.magic_vente_stock.metierServices;

import com.gondor.magic_vente_stock.repository.DetailPanierRepo;
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
        name = "t_detail_panier"
)
public class DetailPanier {
    @Id
    private String id="";

    @ManyToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "panier_id",
            referencedColumnName = "id"
    )
    private Panier panier;

    @ManyToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "produit_id",
            referencedColumnName = "id"
    )
    private Produit produit;

    Integer nombre;

    public DetailPanier(Produit produit, int quantite, Panier panier){
        this.produit = produit;
        this.nombre = quantite;
        this.panier = panier;
    }
}
