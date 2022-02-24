package com.example.javaassignmenttwo.data.repository;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnectionFactory
{
    Connection getConnection() throws SQLException;
}
