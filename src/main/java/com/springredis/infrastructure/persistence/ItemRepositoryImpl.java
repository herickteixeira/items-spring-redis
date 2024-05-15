package com.springredis.infrastructure.persistence;

import com.springredis.domain.Item;
import com.springredis.domain.repository.ItemRepository;
import com.springredis.infrastructure.mappers.ItemEntityMapper;
import com.springredis.infrastructure.mappers.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    @Autowired
    private ItemRepositoryJpa itemRepositoryJpa;

    @Override
    public List<Item> getAllItems() {
        var itemEntities = itemRepositoryJpa.findAll();
        return ItemMapper.map(itemEntities);
    }

    @Override
    public Item getItemById(Long id) {
        var optionalItemEntity = itemRepositoryJpa.findById(id);
        return optionalItemEntity.map(ItemMapper::map).orElse(null);
    }

    @Override
    public void addItem(Item item) {
        itemRepositoryJpa.save(ItemEntityMapper.map(item));
    }

    @Override
    public void updateItem(Long id, Item item) {
        itemRepositoryJpa.save(ItemEntityMapper.map(item));
    }

    @Override
    public void deleteItemById(Long id) {
        itemRepositoryJpa.deleteById(id);
    }
}
