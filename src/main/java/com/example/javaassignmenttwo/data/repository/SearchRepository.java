package com.example.javaassignmenttwo.data.repository;

import com.example.javaassignmenttwo.model.CustomerCountry;
import com.example.javaassignmenttwo.model.SearchTrack;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class SearchRepository
{
    private final DatabaseConnectionFactory connectionFactory;

    public SearchRepository(DatabaseConnectionFactory connectionFactory)
    {
        this.connectionFactory = connectionFactory;
    }

    //Connection conn = null;

    // We search the DB for tracks that fits the search input from search.html.
    public ArrayList<SearchTrack> findTracks(String input) //throws SQLException
    {
        ArrayList<SearchTrack> queryResult = new ArrayList<SearchTrack>();


        try (Connection conn = connectionFactory.getConnection())
        {

            PreparedStatement prepStatement = conn.prepareStatement("SELECT Track.Name AS TrackName, Artist.Name AS ArtistName, Album.Title AS AlbumTitle, Genre.Name AS GenreName FROM Track INNER JOIN Genre ON Track.GenreId = Genre.GenreId INNER JOIN Album on Album.AlbumId = Track.AlbumId INNER JOIN Artist ON Artist.ArtistId = Album.ArtistId WHERE Track.Name LIKE ?");
            prepStatement.setString(1,input);
            ResultSet resultset = prepStatement.executeQuery();

            while (resultset.next()) {
                String trackname = resultset.getString("TrackName");
                String artistname = resultset.getString("ArtistName");
                String albumtitle = resultset.getString("AlbumTitle");
                String genrename = resultset.getString("GenreName");

                queryResult.add(new SearchTrack(trackname, artistname, albumtitle, genrename));
                System.out.printf("Track, artist, album and genre names and titles {%s,%s,%s,%s} \n", trackname, artistname, albumtitle, genrename);
            }
        }
        catch (Exception ex) {
            System.out.println("Something went wrong...");
            ex.printStackTrace();
            System.out.println(ex.toString());
            return null; // error returns null
        }
        finally {
            /*
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            */

            return queryResult;
        }
    }
}
