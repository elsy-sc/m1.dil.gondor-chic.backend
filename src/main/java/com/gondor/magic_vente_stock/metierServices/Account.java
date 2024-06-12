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
        name = "account"
)
public class Account {
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

    public Account(String nom, String prenom, String login, String password, Role r) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = login;
        this.motDePasse = password;
        this.role = r;
    }

    public Account(String nom, String prenom, String login, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = login;
        this.motDePasse = password;
    }

    @ManyToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "role_id",
            referencedColumnName = "id"
    )
    private Role role;
}
