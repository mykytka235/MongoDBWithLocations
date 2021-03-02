package com.lootfood.api.transformer;

import com.lootfood.api.dto.ServiceDto;
import com.lootfood.db.entity.Service;

public class ServiceTransformer {
    public static Service transform(ServiceDto dto) {
        return transform(dto.getId(), dto);
    }

    public static Service transform(String id, ServiceDto dto) {
        return Service.builder().id(id)
                .name(dto.getName())
                .createdDate(dto.getCreatedDate())
                .updateDate(dto.getUpdateDate()).build();
    }

    public static ServiceDto transform(Service service) {
        return transform(service.getId(), service);
    }

    public static ServiceDto transform(String id, Service service) {
        return ServiceDto.builder().id(service.getId())
                .name(service.getName())
                .createdDate(service.getCreatedDate())
                .updateDate(service.getUpdateDate()).build();
    }
}
