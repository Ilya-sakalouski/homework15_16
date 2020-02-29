package ru.mail.ilya6697089.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.mail.ilya6697089.ConnectionRepository;
import ru.mail.ilya6697089.ItemRepository;
import ru.mail.ilya6697089.ItemService;
import ru.mail.ilya6697089.model.Item;
import ru.mail.ilya6697089.model.ItemDTO;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private ConnectionRepository connectionRepository;
    private ItemRepository itemRepository;

    public ItemServiceImpl(
            ConnectionRepository connectionRepository,
            ItemRepository itemRepository
    ) {
        this.connectionRepository = connectionRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void add(ItemDTO itemDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Item item = getObjectFromDTO(itemDTO);
                itemRepository.add(connection, item);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private Item getObjectFromDTO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        return item;
    }

    @Override
    public List<ItemDTO> findAll() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<Item> items = itemRepository.findAll(connection);
                List<ItemDTO> itemDTOList = conversDTOtoItem(items);
                connection.commit();
                return itemDTOList;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    private List<ItemDTO> conversDTOtoItem(List<Item> items) {
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : items) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setName(item.getName());
            itemDTO.setDescription(item.getDescription());
            itemDTOList.add(itemDTO);
        }
        return itemDTOList;
    }

}
