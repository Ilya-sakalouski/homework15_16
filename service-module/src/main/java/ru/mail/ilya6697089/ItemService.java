package ru.mail.ilya6697089;

import java.util.List;

import ru.mail.ilya6697089.model.ItemDTO;

public interface ItemService {

    void add(ItemDTO itemDTO);

    List<ItemDTO> findAll();

}
