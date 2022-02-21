package com.example.javaassignmenttwo;

public class Genre {
    //---attributes
    private String genreId;
    private String name;

    //---constructors
    public Genre(String genreID, String Name){
        this.genreId = genreID;
        this.name = Name;
    }

    //---setters
    public void setGenreId(String genreID) {
        this.genreId = genreID;
    }
    public void setName(String name) {
        this.name = name;
    }

    //---getters
    public String getGenreId() {
        return this.genreId;
    }
    public String getName() {
        return this.name;
    }
}
