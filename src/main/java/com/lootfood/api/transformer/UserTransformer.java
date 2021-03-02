package com.lootfood.api.transformer;

import com.lootfood.api.dto.UserDto;
import com.lootfood.db.entity.User;

public class UserTransformer {
    public static User transform(UserDto dto) {
        return transform(dto.getId(), dto);
    }

    public static User transform(String id, UserDto dto) {
        return User.builder().id(id)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .createdDate(dto.getCreatedDate())
                .updateDate(dto.getUpdateDate()).build();
    }

    public static UserDto transform(User user) {
        return transform(user.getId(), user);
    }

    public static UserDto transform(String id, User user) {
        return UserDto.builder().id(id)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .createdDate(user.getCreatedDate())
                .updateDate(user.getUpdateDate()).build();
    }
}
