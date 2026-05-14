package org.test.bai3;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ItemService {

    private final Map<Long, Item> items = new HashMap<>();

    public ItemService() {

        items.put(1L, new Item(1L, "Laptop", 10));
        items.put(2L, new Item(2L, "Mouse", 20));
    }

    public Item getItemById(Long id) {
        return items.get(id);
    }

    public Item createItem(Item item) {
        items.put(item.getId(), item);
        return item;
    }

    public Item updateItem(Long id, Item item) {

        if (!items.containsKey(id)) {
            return null;
        }

        item.setId(id);
        items.put(id, item);

        return item;
    }

    public boolean deleteItem(Long id) {

        if (!items.containsKey(id)) {
            return false;
        }

        items.remove(id);

        return true;
    }
}
