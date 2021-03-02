package com.lootfood.api.controller;

import com.lootfood.api.dto.OrderDto;
import com.lootfood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static com.lootfood.api.transformer.OrderTransformer.transform;

@RestController
@RequestMapping("/api/lootfood/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderDto create(@RequestBody OrderDto dto) {
        return transform(orderService.add(transform(dto)));
    }

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable("id") String id, @RequestBody OrderDto dto) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return transform(id, orderService.update(transform(id, dto)));
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable("id") String id) {
        return transform(orderService.getById(id));
    }

    @GetMapping(value = "/all")
    public Page<OrderDto> getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return orderService.getAll(PageRequest.of(page, size)).map(e -> transform(e));
    }
}