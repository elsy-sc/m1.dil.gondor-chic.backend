package com.gondor.magic_vente_stock.repository;

import com.gondor.magic_vente_stock.metierServices.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
    Role findByNom(String nom);
}
