package com.lootfood.service;

import com.lootfood.entity.Order;
import com.lootfood.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order add(Order order) {
        return orderRepository.save(order);
    }

    public Order getById(String id) {
        return orderRepository.findById(id).get();
    }

    public Page<Order> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Order update(Order order) {
        Order orderFromDb = orderRepository.findById(order.getId()).get();
        orderFromDb.setName(order.getName());
        orderFromDb.setDescription(order.getDescription());
        orderFromDb.setUser(order.getUser());
        orderFromDb.setService(order.getService());

        return orderRepository.save(orderFromDb);
    }

    public void delete(String id) {
        orderRepository.deleteById(id);
    }
}
