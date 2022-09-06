package com.guipalm4.infrastructure.repository;

import com.guipalm4.infrastructure.persistence.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductJpaEntity, String> {

    Optional<ProductJpaEntity> findByNameIgnoreCase(String name);
}
