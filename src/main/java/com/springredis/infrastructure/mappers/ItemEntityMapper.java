package com.springredis.infrastructure.mappers;

import com.springredis.domain.Item;
import com.springredis.infrastructure.Entities.ItemEntity;

import java.util.ArrayList;
import java.util.List;

public class ItemEntityMapper {

    public static ItemEntity map(Item item) {
        return new ItemEntity(item.getId(), item.getName(), item.getDescription());
    }

    public static List<ItemEntity> map(List<Item> items) {
        var mappedItems = new ArrayList<ItemEntity>();
        for (var item : items)
            mappedItems.add(map(item));
        return mappedItems;
    }
}