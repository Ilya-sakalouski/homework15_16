package ru.mail.ilya6697089;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ru.mail.ilya6697089.model.Item;

public interface ItemRepository {

    void add(Connection connection, Item item) throws SQLException;

    List<Item> findAll(Connection connection) throws SQLException;

}
