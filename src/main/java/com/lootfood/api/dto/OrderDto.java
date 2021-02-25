package com.lootfood.api.dto;

import com.lootfood.db.entity.Service;
import com.lootfood.db.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String id;
    private String name;
    private String description;
    private User user;
    private Service service;
    private Date createdDate;
    private Date updateDate;
}
