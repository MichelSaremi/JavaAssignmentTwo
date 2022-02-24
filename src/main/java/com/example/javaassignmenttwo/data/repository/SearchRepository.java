package com.example.javaassignmenttwo.data.repository;

import com.example.javaassignmenttwo.model.SearchTrack;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class SearchRepository
{
    //---Attributes
    private final DatabaseConnectionFactory connectionFactory;

    //---constructor
    public SearchRepository(DatabaseConnectionFactory connectionFactory)
    {
        this.connectionFactory = connectionFactory;
    }


    //---We search the DB for tracks that fits the search input from search.html.
    public ArrayList<SearchTrack> findTracks(String input)
    {
        ArrayList<SearchTrack> queryResult = new ArrayList<SearchTrack>();

        //---manage connection
        try (Connection conn = connectionFactory.getConnection())
        {

            //---prepare statement and execute
            PreparedStatement prepStatement = conn.prepareStatement("SELECT Track.Name AS TrackName, Artist.Name AS ArtistName, Album.Title AS AlbumTitle, Genre.Name AS GenreName FROM Track INNER JOIN Genre ON Track.GenreId = Genre.GenreId INNER JOIN Album on Album.AlbumId = Track.AlbumId INNER JOIN Artist ON Artist.ArtistId = Album.ArtistId WHERE Track.Name LIKE ?");
            prepStatement.setString(1,input);
            ResultSet resultset = prepStatement.executeQuery();

            //---Manage results and build new searchTrack objects
            //---add these to queryResult
            while (resultset.next()) {
                String trackname = resultset.getString("TrackName");
                String artistname = resultset.getString("ArtistName");
                String albumtitle = resultset.getString("AlbumTitle");
                String genrename = resultset.getString("GenreName");

                queryResult.add(new SearchTrack(trackname, artistname, albumtitle, genrename));

            }
        }
        catch (Exception ex) {
            System.out.println("Something went wrong...");
            ex.printStackTrace();
            System.out.println(ex.toString());
            return null; // error returns null
        }

            return queryResult;

    }
}
