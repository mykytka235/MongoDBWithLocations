package com.lootfood.service;

import com.lootfood.db.entity.Delivery;
import com.lootfood.db.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public Delivery add(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public Delivery getById(String id) {
        return deliveryRepository.findById(id).get();
    }

    public Page<Delivery> getAll(Pageable pageable) {
        return deliveryRepository.findAll(pageable);
    }

    public Delivery update(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }
}
