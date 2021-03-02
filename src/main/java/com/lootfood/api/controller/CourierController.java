package com.lootfood.api.controller;

import com.lootfood.api.dto.CourierDto;
import com.lootfood.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static com.lootfood.api.transformer.CourierTransformer.transform;

@RestController
@RequestMapping("/api/lootfood/courier")
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;

    @PostMapping
    public CourierDto create(@RequestBody CourierDto dto) {
        return transform(courierService.add(transform(dto)));
    }

    @PutMapping("/{id}")
    public CourierDto update(@PathVariable("id") String id, @RequestBody CourierDto dto)  {
        return transform(id, courierService.update(transform(id, dto)));
    }

    @GetMapping("/{id}")
    public CourierDto getById(@PathVariable("id") String id) {
        return transform(courierService.getById(id));
    }

    @GetMapping(value = "/all")
    public Page<CourierDto> getAll(@RequestParam(defaultValue = "0") Integer page,
                                   @RequestParam(defaultValue = "10") Integer size) {
        return courierService.getAll(PageRequest.of(page, size)).map(e -> transform(e));
    }
}
