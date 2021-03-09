package com.lootfood.api.transformer;

import com.lootfood.api.dto.UserDto;
import com.lootfood.db.entity.User;

public class UserTransformer {
    public static User transform(UserDto dto) {
        return transform(dto.getId(), dto);
    }

    public static User transform(String id, UserDto dto) {
        return User.builder().id(id)
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .imageUrl(dto.getImageUrl())
                .createdDate(dto.getCreatedDate())
                .updateDate(dto.getUpdateDate())
                .build();
    }

    public static UserDto transform(User user) {
        return transform(user.getId(), user);
    }

    public static UserDto transform(String id, User user) {
        return UserDto.builder().id(id)
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .imageUrl(user.getImageUrl())
                .createdDate(user.getCreatedDate())
                .updateDate(user.getUpdateDate())
                .build();
    }
}
