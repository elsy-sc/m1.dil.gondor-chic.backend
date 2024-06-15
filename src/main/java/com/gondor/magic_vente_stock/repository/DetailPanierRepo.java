package com.gondor.magic_vente_stock.repository;


import com.gondor.magic_vente_stock.metierServices.DetailPanier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailPanierRepo extends JpaRepository<DetailPanier,String> {

    @Query(
            value = "select * from t_detail_panier where panier_id = ?1",
            nativeQuery = true
    )
    public List<DetailPanier> getListDetailCurrentBasket(String idPanier);

}
