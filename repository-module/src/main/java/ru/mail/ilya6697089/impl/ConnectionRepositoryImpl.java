package ru.mail.ilya6697089.impl;

import ru.mail.ilya6697089.ConnectionRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class ConnectionRepositoryImpl implements ConnectionRepository {

    private final DataSource dataSource;

    public ConnectionRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
