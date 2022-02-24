package com.example.javaassignmenttwo.controller;

import com.example.javaassignmenttwo.data.services.SearchServiceImpl;
import com.example.javaassignmenttwo.model.SearchTrack;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class SearchController
{
    private final SearchServiceImpl searchService;

    //---constructor
    public SearchController(SearchServiceImpl searchService)
    {
        this.searchService = searchService;
    }

    //---search for tracks
    @GetMapping("/search/{input}")
    public ArrayList<SearchTrack> searchForTracks(@PathVariable String input) throws SQLException
    {
        return searchService.getSearchedTracks(input);
    }
}
