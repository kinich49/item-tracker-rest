package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.ItemDto;
import mx.kinich49.itemtracker.repositories.ItemRepository;
import mx.kinich49.itemtracker.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Optional<List<ItemDto>> findLike(String name) {
        if (name == null || name.length() == 0)
            return Optional.empty();

        return Optional.ofNullable(itemRepository.findByNameStartsWithIgnoreCase(name))
                .map(categories ->
                        categories.stream()
                                .map(ItemDto::from)
                                .collect(Collectors.toList())
                );
    }
}
