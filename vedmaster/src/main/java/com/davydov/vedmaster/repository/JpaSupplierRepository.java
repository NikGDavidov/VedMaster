package com.davydov.vedmaster.repository;

import com.davydov.vedmaster.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSupplierRepository extends JpaRepository<Supplier,Long> {
}
