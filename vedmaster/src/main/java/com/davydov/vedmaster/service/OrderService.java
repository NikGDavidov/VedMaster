package com.davydov.vedmaster.service;

import com.davydov.vedmaster.calculation.OrderDAO;

import com.davydov.vedmaster.repository.JpaOrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class OrderService {
    @Autowired
    JpaOrderRepository orderRepository;
    public List<OrderDAO> getOrders() {
        return orderRepository.findAll();
    }

    public OrderDAO getById(long id) {
        Optional<OrderDAO> findOrder = orderRepository.findById(id);
        if (findOrder.isEmpty()) {
            throw new NoSuchElementException("Не найден заказ с идентификатором \"" + id + "\"");
        }
        return findOrder.get();
    }
}
