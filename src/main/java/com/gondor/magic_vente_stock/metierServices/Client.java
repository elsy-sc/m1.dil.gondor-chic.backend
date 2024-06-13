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
    @Id
    @SequenceGenerator(
            name = "account_id_sequence",
            sequenceName = "account_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_id_sequence"
    )
    private Integer id;
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
