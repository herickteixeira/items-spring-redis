package com.springredis.application.service;

import com.springredis.application.exceptions.ItemNotFoundException;
import com.springredis.domain.Item;
import com.springredis.domain.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemService.class);
    private static final String REDIS_KEY_PREFIX = "item:";
    private static final String REDIS_ALL_ITEMS_KEY = "Items:";
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void addItem(Item item) {
        itemRepository.addItem(item);
        updateCache();
    }

    public List<Item> getAllItems() {
        List<Item> allItemsFromCache = (List<Item>) redisTemplate.opsForValue().get(REDIS_ALL_ITEMS_KEY);
        if (allItemsFromCache != null) {
            log.info("Cached");
            return allItemsFromCache;
        }

        List<Item> allItems = itemRepository.getAllItems();

        redisTemplate.opsForValue().set(REDIS_ALL_ITEMS_KEY, allItems);
        log.info("Without cache");
        return allItems;
    }

    public Item getItemById(Long id) {
        var itemFromCache = (Item) redisTemplate.opsForValue().get(REDIS_KEY_PREFIX + id);
        if (itemFromCache != null) {
            log.info("Cached");
            return itemFromCache;
        }

        var item = itemRepository.getItemById(id);
        if (item == null) {
            throw new ItemNotFoundException("Item with ID " + id + " not found");
        }

        redisTemplate.opsForValue().set(REDIS_KEY_PREFIX + id, item);
        log.info("Without cache");
        return item;
    }

    public void updateItem(Long id, Item item) {
        var itemEntity = itemRepository.getItemById(id);
        if (itemEntity == null) {
            throw new ItemNotFoundException("Item with ID " + id + " not found");
        }
        itemRepository.updateItem(id, item);
    }

    public void deleteItem(Long id) {
        var itemEntity = itemRepository.getItemById(id);
        if (itemEntity == null) {
            throw new ItemNotFoundException("Item with ID " + id + " not found");
        }

        var itemFromCache = redisTemplate.opsForValue().get(REDIS_KEY_PREFIX + id);
        if (itemFromCache != null) {
            redisTemplate.delete(REDIS_KEY_PREFIX + id);
        }
        itemRepository.deleteItemById(id);
        updateCache();
    }

    private void updateCache() {
        List<Item> allItems = itemRepository.getAllItems();
        redisTemplate.opsForValue().set(REDIS_ALL_ITEMS_KEY, allItems);
    }
}