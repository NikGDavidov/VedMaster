package com.davydov.vedmaster.service;

import com.davydov.vedmaster.models.Item;
import com.davydov.vedmaster.repository.JpaItemRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class ItemService {
    @Autowired
    JpaItemRepository itemRepository;

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getById(long id) {
        Optional<Item> findItem = itemRepository.findById(id);
        if (findItem.isEmpty()) {
            throw new NoSuchElementException("Не найден элемент с идентификатором \"" + id + "\"");
        }

        return findItem.get();

    }
}