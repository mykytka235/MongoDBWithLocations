package com.lootfood.api.controller;

import com.lootfood.api.dto.ServiceDto;
import com.lootfood.service.ServiceOfService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

import static com.lootfood.api.transformer.ServiceTransformer.transform;

@RestController
@RequestMapping("/api/lootfood/service")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceOfService service;

    @PostMapping
    public ServiceDto create(@RequestBody ServiceDto dto) {
        return transform(service.add(transform(dto)));
    }

    @PutMapping("/{id}")
    public ServiceDto update(@PathVariable("id") String id, @RequestBody ServiceDto dto) {
        return transform(id, service.update(transform(id, dto)));
    }

    @GetMapping("/{id}")
    public ServiceDto getById(@PathVariable("id") String id) {
        return transform(service.getById(id));
    }

    @GetMapping(value = "/all")
    public Page<ServiceDto> getAll(Integer page, Integer size) {
        return service.getAll(PageRequest.of(page, size)).map(e -> transform(e));
    }
}