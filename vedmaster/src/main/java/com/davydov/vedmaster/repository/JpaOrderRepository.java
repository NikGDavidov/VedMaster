package com.davydov.vedmaster.repository;

import com.davydov.vedmaster.calculation.OrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderDAO,Long> {
}
