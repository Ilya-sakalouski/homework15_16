package ru.mail.ilya6697089;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionRepository {

    Connection getConnection() throws SQLException;

}
