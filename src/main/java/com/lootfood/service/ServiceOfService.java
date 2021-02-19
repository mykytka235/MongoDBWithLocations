package com.lootfood.service;

import com.lootfood.entity.Service;
import com.lootfood.repository.service.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceOfService {
    private final ServiceRepository serviceRepository;

    public Service add(Service service) {
        return serviceRepository.save(service);
    }

    public Service getById(String id) {
        return serviceRepository.findById(id).get();
    }

    public Page<Service> getAll(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    public Service update(Service service) {
        return serviceRepository.update(service);
    }

    public void delete(String id) {
        serviceRepository.deleteById(id);
    }
}
