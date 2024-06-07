package com.gondor.magic_vente_stock.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Slf4j
public class UtilRepository {
    @PersistenceContext
    private EntityManager entityManager;

}
