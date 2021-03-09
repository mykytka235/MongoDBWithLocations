package com.lootfood.api.controller;

import com.lootfood.api.dto.DeliveryDto;
import com.lootfood.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static com.lootfood.api.transformer.DeliveryTransformer.transform;

@RestController
@RequestMapping("/api/lootfood/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping
    public DeliveryDto create(@RequestBody DeliveryDto dto) {
        return transform(deliveryService.add(transform(dto)));
    }

    @PutMapping("/{id}")
    public DeliveryDto update(@PathVariable("id") String id, @RequestBody DeliveryDto dto)  {
        return transform(id, deliveryService.update(transform(id, dto)));
    }

    @GetMapping("/{id}")
    public DeliveryDto getById(@PathVariable("id") String id) {
        return transform(deliveryService.getById(id));
    }

    @GetMapping(value = "/all")
    public Page<DeliveryDto> getAll(Integer page, Integer size) {
        return deliveryService.getAll(PageRequest.of(page, size)).map(e -> transform(e));
    }
}
