package ru.mail.ilya6697089.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mail.ilya6697089.ItemRepository;
import ru.mail.ilya6697089.model.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void add(Connection connection, Item user) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Item(name,description) VALUES (?,?)"
        )) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getDescription());
            int rowAffected = preparedStatement.executeUpdate();
            logger.info("Row affected: " + rowAffected);
        }
    }

    @Override
    public List<Item> findAll(Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT name,description FROM Item"
        )) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Item> items = new ArrayList<>();
                while (resultSet.next()) {
                    Item item = new Item();
                    item.setName(resultSet.getString("name"));
                    items.add(item);
                }
                return items;
            }
        }
    }

}
