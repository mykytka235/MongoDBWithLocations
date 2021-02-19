package com.lootfood.api.controller;

import com.lootfood.api.Dto.OrderDto;
import com.lootfood.entity.Order;
import com.lootfood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.lootfood.api.transformer.OrderTransformer.transform;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderDto add(@RequestBody OrderDto dto) {
        return transform(orderService.add(transform(dto)));
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable("id") String id) {
        return transform(orderService.getById(id));
    }

    @GetMapping(value = "/all", params = { "page", "size" })
    public Page<OrderDto> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderService.getAll(pageable);

        return new PageImpl<OrderDto>(orders.stream().map(order -> transform(order)).collect(Collectors.toList()),
                pageable, orders.getTotalElements());
    }

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable("id") String id, @RequestBody OrderDto dto) {
        return transform(id, orderService.update(transform(id, dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        orderService.delete(id);
    }
}