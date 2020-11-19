package com.example.demo.mapper;

import com.example.demo.dto.ItemDto;
import com.example.demo.entity.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto toDto(Item item);
    List<ItemDto> toDto(List<Item> item);

    Item toEntity(ItemDto itemDto);
    List<Item> toEntity(List<ItemDto> itemDto);

}
