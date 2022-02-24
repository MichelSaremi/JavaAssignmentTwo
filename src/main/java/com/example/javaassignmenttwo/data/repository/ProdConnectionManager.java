package com.example.javaassignmenttwo.data.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//---connection manager for production purposes
@Component
@Profile("production")
public class ProdConnectionManager implements DatabaseConnectionFactory
{
    static final String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";

    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL);
    }
}