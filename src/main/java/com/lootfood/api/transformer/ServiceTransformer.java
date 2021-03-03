package com.lootfood.api.transformer;

import com.lootfood.api.dto.ServiceDto;
import com.lootfood.db.entity.Service;

public class ServiceTransformer {
    public static Service transform(ServiceDto dto) {
        return transform(dto.getId(), dto);
    }

    public static Service transform(String id, ServiceDto dto) {
        return Service.builder().id(id)
                .companyId(dto.getCompanyId())
                .name(dto.getName())
                .location(dto.getLocation())
                .deliveries(dto.getDeliveries())
                .build();
    }

    public static ServiceDto transform(Service service) {
        return transform(service.getId(), service);
    }

    public static ServiceDto transform(String id, Service service) {
        return ServiceDto.builder().id(id)
                .companyId(service.getCompanyId())
                .name(service.getName())
                .location(service.getLocation())
                .deliveries(service.getDeliveries())
                .build();
    }
}
