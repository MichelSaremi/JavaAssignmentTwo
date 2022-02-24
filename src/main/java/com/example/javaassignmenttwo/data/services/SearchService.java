package com.example.javaassignmenttwo.data.services;

import com.example.javaassignmenttwo.model.SearchTrack;

import java.util.ArrayList;

public interface SearchService
{
    ArrayList<SearchTrack> getSearchedTracks(String input);
}
