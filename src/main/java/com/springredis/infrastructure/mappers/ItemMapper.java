package com.springredis.infrastructure.mappers;

import com.springredis.domain.Item;
import com.springredis.infrastructure.Entities.ItemEntity;

import java.util.ArrayList;
import java.util.List;

public class ItemMapper {

    public static Item map(ItemEntity itemEntity) {
        return new Item(itemEntity.getId(), itemEntity.getName(), itemEntity.getDescription());
    }

    public static List<Item> map(List<ItemEntity> itemsEntities) {
        var items = new ArrayList<Item>();
        for (var itemEntity : itemsEntities)
            items.add(map(itemEntity));
        return items;
    }
}
