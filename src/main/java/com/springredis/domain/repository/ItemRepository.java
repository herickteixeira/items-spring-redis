package com.springredis.domain.repository;

import com.springredis.domain.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> getAllItems();

    Item getItemById(Long id);

    void addItem(Item item);

    void updateItem(Long id, Item item);

    void deleteItemById(Long id);

}
