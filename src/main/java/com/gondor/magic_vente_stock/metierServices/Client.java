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
        name = "t_client"
)
public class Client {

    //change sequence name to client not account
    @Id
    private String id = "";
    private String nom;
    private String prenom;

    @Column(
            name = "pseudo",
            nullable = false
    )
    private String pseudo;

    @Column(
            name = "motdepasse",
            nullable = false
    )
    private String motDePasse;

    public Client(String nom, String prenom, String login, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = login;
        this.motDePasse = password;
    }

}
