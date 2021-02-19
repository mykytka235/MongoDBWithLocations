package com.lootfood.api.controller;

import com.lootfood.api.Dto.UserDto;
import com.lootfood.entity.User;
import com.lootfood.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.lootfood.api.transformer.UserTransformer.transform;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto add(@RequestBody UserDto dto) {
        return transform(userService.add(transform(dto)));
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") String id) {
        return transform(userService.getById(id));
    }

    @GetMapping(value = "/all", params = { "page", "size" })
    public Page<UserDto> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userService.getAll(pageable);

        return new PageImpl<UserDto>(users.stream().map(user -> transform(user)).collect(Collectors.toList()), pageable,
                users.getTotalElements());
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id") String id, @RequestBody UserDto dto) {
        return transform(id, userService.update(transform(id, dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        userService.delete(id);
    }
}