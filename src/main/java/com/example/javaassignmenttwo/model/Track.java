package com.example.javaassignmenttwo.model;

public class Track {
    //---attributes
    private String genreId;
    private String trackId;

    //---constructors
    public Track(String genreID, String trackID){
        this.genreId = genreID;
        this.trackId = trackID;
    }

    //---setters
    public void setGenreId(String genreID) {
        this.genreId = genreID;
    }
    public void setTrackId(String trackId) {this.trackId = trackId; }

    //---getters
    public String getGenreId() {
        return this.genreId;
    }
    public String getTrackId() {
        return this.trackId;
    }
}
