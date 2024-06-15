package com.gondor.magic_vente_stock.repository;


import com.gondor.magic_vente_stock.metierServices.DetailPanier;
import com.gondor.magic_vente_stock.metierServices.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanierRepo extends JpaRepository<Panier,String> {
    @Query(
            value = "select * from t_panier where client_id = ?1 and etat = false limit 1",
            nativeQuery = true
    )
    public Panier getCurrentBasket(String idClient);
}
