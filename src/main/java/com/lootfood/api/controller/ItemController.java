package com.lootfood.api.controller;

import com.lootfood.api.dto.ItemDto;
import com.lootfood.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static com.lootfood.api.transformer.ItemTransformer.transform;

@RestController
@RequestMapping("/api/lootfood/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ItemDto create(@RequestBody ItemDto dto) {
        return transform(itemService.add(transform(dto)));
    }

    @PutMapping("/{id}")
    public ItemDto update(@PathVariable("id") String id, @RequestBody ItemDto dto)  {
        return transform(id, itemService.update(transform(id, dto)));
    }

    @GetMapping("/{id}")
    public ItemDto getById(@PathVariable("id") String id) {
        return transform(itemService.getById(id));
    }

    @GetMapping(value = "/all")
    public Page<ItemDto> getAll(@RequestParam(defaultValue = "0") Integer page,
                                   @RequestParam(defaultValue = "10") Integer size) {
        return itemService.getAll(PageRequest.of(page, size)).map(e -> transform(e));
    }
}
