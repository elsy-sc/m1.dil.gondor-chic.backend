package com.gondor.magic_vente_stock.model;

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


    private String email;

    @Column(
            name = "username",
            nullable = false
    )
    private String username;

    private String password;

    public Account(String nom, String prenom, String email, String login, String password, Role r) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.username = login;
        this.password = password;
        this.role = r;
    }

    public Account(String nom, String prenom, String email, String login, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.username = login;
        this.password = password;
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
