package com.example.javaassignmenttwo.data.repository;

import com.example.javaassignmenttwo.model.Customer;
import com.example.javaassignmenttwo.model.Genre;
import com.example.javaassignmenttwo.model.Music;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class MusicRepositoryImpl implements MusicRepository {
    private final DatabaseConnectionFactory connectionFactory;

    public MusicRepositoryImpl(DatabaseConnectionFactory connectionFactory)
    {
        this.connectionFactory = connectionFactory;
    }

    // Setup
    static final String URL = "jdbc:sqlite:src/main/resources/Chinook_Sqlite.sqlite";
    Connection conn = null;

    //---read all music from database
    public ArrayList<Music> selectAllMusic(){
        ArrayList<Music> musics = new ArrayList<Music>();

        try {
            // Open Connection
            conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT TrackId, Name, Composer, GenreId FROM Track");
            
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                musics.add(
                        new Music(
                                resultSet.getString("TrackId"),
                                resultSet.getString("Name"),
                                resultSet.getString("Composer"),
                                resultSet.getString("GenreId")
                        ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return musics;
        }
    }
    
    //---read specific music from database
    public Music selectSpecificMusic(int TrackId){

        Music music = null;
        try {
            // Open Connection
            conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT TrackId, Name, Composer, GenreId FROM Track WHERE TrackId=?");

            preparedStatement.setInt(1, TrackId);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            music =new Music(
                                resultSet.getString("TrackId"),
                                resultSet.getString("Name"),
                                resultSet.getString("Composer"),
                                resultSet.getString("GenreId")
                        );
            
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return music;
        }
    }

    //---reading all genre from database
    public ArrayList<Genre> selectAllGenre(){
        ArrayList<Genre> allgenre = new ArrayList<Genre>();

        try {
            // Open Connection
            conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT GenreId,Name FROM Genre");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                allgenre.add(
                        new Genre(
                        resultSet.getString("GenreId"),
                        resultSet.getString("Name")
                ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return allgenre;
        }
    }


    public Genre selectSpecificGenre(int genreID){
        Genre genre = null;

        try (Connection conn = connectionFactory.getConnection()){
            // Open Connection
            //conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT GenreId,Name FROM Genre WHERE GenreId = ?");
            preparedStatement.setInt(1, genreID);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                genre = new Genre(
                        resultSet.getString("GenreId"),
                        resultSet.getString("Name")
                );
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return genre;
        }
    }

}
