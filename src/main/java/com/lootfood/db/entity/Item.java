package com.lootfood.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "item")
public class Item {
    @Id
    private String id;
    private String name;
    private String serviceId;
    private String description;
    private String imageUrl;
    private Number price;
}
