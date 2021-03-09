package com.lootfood.service;

import com.lootfood.db.entity.Courier;
import com.lootfood.db.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final CourierRepository courierRepository;

    public Courier add(Courier courier) {
        return courierRepository.save(courier);
    }

    public Courier getById(String id) {
        return courierRepository.findById(id).get();
    }

    public Page<Courier> getAll(Pageable pageable) {
        return courierRepository.findAll(pageable);
    }

    public Courier update(Courier courier) {
        return courierRepository.save(courier);
    }
}
