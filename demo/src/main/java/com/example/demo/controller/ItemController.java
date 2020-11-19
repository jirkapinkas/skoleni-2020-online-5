package com.example.demo.controller;

import com.example.demo.dto.ItemDto;
import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
//@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    private final ItemRepository itemRepository;

    public ItemController(ItemService itemService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    // http://localhost:8080/item
    @GetMapping
    public List<ItemDto> items() {
        return itemService.findAll(Sort.by("id"));
    }

    // http://localhost:8080/item?sort=name
    @GetMapping(params = {"sort"})
    public List<ItemDto> items(@RequestParam String sort) {
        return itemService.findAll(Sort.by(Sort.Direction.ASC, sort));
    }

    // http://localhost:8080/item?sort=name&direction=asc
    @GetMapping(params = {"sort", "direction"})
    public List<ItemDto> items(@RequestParam String sort, @RequestParam String direction) {
        return itemService.findAll(Sort.by(Sort.Direction.fromString(direction), sort));
    }

    // http://localhost:8080/item/1
    @GetMapping("/{id}")
    public Optional<ItemDto> item(@PathVariable int id) {
        return itemService.findById(id);
    }

    // http://localhost:8080/item/1
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        itemService.deleteById(id);
    }

    // http://localhost:8080/item
    @PostMapping
    public ItemDto insert(@Valid @RequestBody ItemDto itemDto) {
        itemDto.setId(0); // ID (klic) se nastavi na nulu => operace save() bude provadet insert
        return itemService.save(itemDto);
    }

    // http://localhost:8080/item/1
    @PutMapping("/{id}")
    public ItemDto update(@Valid @RequestBody ItemDto itemDto, @PathVariable int id) {
        itemDto.setId(id); // ID (klic) se natvrdo nastavi na hodnotu atributu "id"
        return itemService.save(itemDto);
    }

    // PUT vs PATCH:
    // https://stackoverflow.com/questions/28459418/use-of-put-vs-patch-methods-in-rest-api-real-life-scenarios

}
