package com.lootfood.service;

import com.lootfood.db.entity.Item;
import com.lootfood.db.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Item add(Item item) {
        return itemRepository.save(item);
    }

    public Item getById(String id) {
        return itemRepository.findById(id).get();
    }

    public Page<Item> getAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Item update(Item item) {
        return itemRepository.save(item);
    }
}
