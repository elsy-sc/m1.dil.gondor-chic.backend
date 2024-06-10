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
        name = "role",
        uniqueConstraints = @UniqueConstraint(
                name = "role_unique",
                columnNames = "nom"
        )
)
public class Role {
    @Id
    /*@SequenceGenerator(
            name = "role_id_sequence",
            sequenceName = "role_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_id_sequence"
    )*/
    private Integer id;

    @Column(
            name = "nom",
            nullable = false
    )
    private String nom;

    public Role(String nom) {
        this.nom = nom;
    }
}
