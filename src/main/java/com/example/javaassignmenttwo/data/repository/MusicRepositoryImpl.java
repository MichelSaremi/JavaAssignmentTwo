package com.example.javaassignmenttwo.data.repository;

import com.example.javaassignmenttwo.model.Genre;
import com.example.javaassignmenttwo.model.Music;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class MusicRepositoryImpl implements MusicRepository {

    //---attributes
    private final DatabaseConnectionFactory connectionFactory;

    //---constructor
    public MusicRepositoryImpl(DatabaseConnectionFactory connectionFactory)
    {
        this.connectionFactory = connectionFactory;
    }


    //---read all music from database
    public ArrayList<Music> selectAllMusic(){
        ArrayList<Music> musics = new ArrayList<Music>();

        //---Manage Connection
        try (Connection conn = connectionFactory.getConnection()){

            System.out.println("Connection to SQLite has been established.");

            //---Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT TrackId, Name, Composer, GenreId FROM Track");
            
            //---Execute Statement
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

            return musics;

    }
    
    //---read specific music from database
    public Music selectSpecificMusic(int TrackId){

        Music music = null;
        //---Manage connection
        try (Connection conn = connectionFactory.getConnection()){


            System.out.println("Connection to SQLite has been established.");

            //---Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT TrackId, Name, Composer, GenreId FROM Track WHERE TrackId=?");

            preparedStatement.setInt(1, TrackId);
            //---Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            //---Process Results
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

            return music;
    }

    //---reading all genre from database
    public ArrayList<Genre> selectAllGenre(){
        ArrayList<Genre> allgenre = new ArrayList<Genre>();

        //---Manage Connection
        try (Connection conn = connectionFactory.getConnection()){

            System.out.println("Connection to SQLite has been established.");

            //---Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT GenreId,Name FROM Genre");
            //---Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            //---Process Results
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

            return allgenre;

    }

    //---select a specific genre based on id
    public Genre selectSpecificGenre(int genreID){
        Genre genre = null;

        //---Manage Connection
        try (Connection conn = connectionFactory.getConnection()){

            System.out.println("Connection to SQLite has been established.");

            //---Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT GenreId,Name FROM Genre WHERE GenreId = ?");
            preparedStatement.setInt(1, genreID);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            //---Process Results
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

            return genre;

    }

}
