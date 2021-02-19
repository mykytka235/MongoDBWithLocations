package com.lootfood.api.controller;

import com.lootfood.api.Dto.ServiceDto;
import com.lootfood.entity.Service;
import com.lootfood.service.ServiceOfService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.lootfood.api.transformer.ServiceTransformer.transform;

@RestController
@RequestMapping("/api/service")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceOfService service;

    @PostMapping
    public ServiceDto add(@RequestBody ServiceDto dto) {
        return transform(service.add(transform(dto)));
    }

    @GetMapping("/{id}")
    public ServiceDto getById(@PathVariable("id") String id) {
        return transform(service.getById(id));
    }

    @GetMapping(value = "/all", params = { "page", "size" })
    public Page<ServiceDto> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Service> services = service.getAll(pageable);

        return new PageImpl<ServiceDto>(services.stream().map(serv -> transform(serv)).collect(Collectors.toList()),
                pageable, services.getTotalElements());
    }

    @PutMapping("/{id}")
    public ServiceDto update(@PathVariable("id") String id, @RequestBody ServiceDto dto) {
        return transform(id, service.update(transform(id, dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }
}