package com.lootfood.api.transformer;

import com.lootfood.api.Dto.ServiceDto;
import com.lootfood.entity.Service;

public class ServiceTransformer {
    public static Service transform(ServiceDto dto) {
        return Service.builder().id(dto.getId())
                .name(dto.getName())
                .createdDate(dto.getCreatedDate())
                .updateDate(dto.getUpdateDate()).build();
    }

    public static Service transform(String id, ServiceDto dto) {
        return Service.builder().id(id)
                .name(dto.getName())
                .createdDate(dto.getCreatedDate())
                .updateDate(dto.getUpdateDate()).build();
    }

    public static ServiceDto transform(Service service) {
        return ServiceDto.builder().id(service.getId())
                .name(service.getName())
                .createdDate(service.getCreatedDate())
                .updateDate(service.getUpdateDate()).build();
    }

    public static ServiceDto transform(String id, Service service) {
        return ServiceDto.builder().id(service.getId())
                .name(service.getName())
                .createdDate(service.getCreatedDate())
                .updateDate(service.getUpdateDate()).build();
    }
}
