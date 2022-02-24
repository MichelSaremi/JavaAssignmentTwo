package com.example.javaassignmenttwo.model;

public class Music {

    //---attributes
    private String trackId;
    private String songName;
    private String artistName;
    private String genreId;

    //---constructors
    public Music(){}

    public Music(String trackID,String name,String artist,String genre){
        this.trackId = trackID;
        this.songName = name;
        this.artistName = artist;
        this.genreId = genre;
    }

    //---setters
    public void setTrackId(String trackId) {this.trackId = trackId; }
    public void setSongName(String songname) {this.songName = songname; }
    public void setArtist(String artistname) {this.artistName = artistname; }
    public void setGenreId(String genreid) {this.genreId = genreid;}

    //---getters
    public String getTrackId() {
        return this.trackId;
    }
    public String getSongName() {
        return this.songName;
    }
    public String getArtistName() {
        return this.artistName;
    }
    public String getGenreId() {
        return this.genreId;
    }
}

