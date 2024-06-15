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
        name = "t_panier"
)
public class Panier {

    @Id
    private String id = "";

    @ManyToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "id"
    )
    private Client client;

    private Boolean etat;

    public Panier(Client client){
        this.client = client;
        this.etat = false;
    }

}
