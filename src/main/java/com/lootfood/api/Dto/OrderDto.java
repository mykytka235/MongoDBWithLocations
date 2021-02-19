package com.lootfood.api.Dto;

import com.lootfood.entity.Service;
import com.lootfood.entity.User;
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
    private User user;
    private Service service;
    private String description;
    private Date createdDate;
    private Date updateDate;
}
