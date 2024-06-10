package com.gondor.magic_vente_stock.repository;

import com.gondor.magic_vente_stock.metierServices.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepo extends JpaRepository<Produit,String> {
    @Query(value = "select * from public.t_produit where estDuJour = True", nativeQuery = true)
    public List<Produit> getAllProductOfTheDay();
}
