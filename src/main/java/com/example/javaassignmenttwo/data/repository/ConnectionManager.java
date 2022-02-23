package com.example.javaassignmenttwo.data.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager
{
    static final String URL = "jdbc:sqlite:src/main/resources/Chinook_Sqlite.sqlite";
    static private ConnectionManager instance;
    private Connection connection;

    static ConnectionManager getInstance()
    {
        if (instance == null)
        {
            instance = new ConnectionManager();
        }
        return instance;
    }

    private ConnectionManager()
    {
        try
        {
            connection = DriverManager.getConnection(URL);
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            System.exit(-1);
        }
    }

    public Connection getConnection()
    {
        return connection;
    }
}