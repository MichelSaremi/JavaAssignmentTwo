package com.example.javaassignmenttwo.data.services;

import com.example.javaassignmenttwo.data.repository.SearchRepository;
import com.example.javaassignmenttwo.model.SearchTrack;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearchServiceImpl implements SearchService
{
    private final SearchRepository searchRepository;

    //---constructor
    public SearchServiceImpl(
            SearchRepository searchRepository
    ) {
        this.searchRepository = searchRepository;
    }

    //---Make and return searchTrack objects based on input
    public ArrayList<SearchTrack> getSearchedTracks(String input)
    {
        ArrayList<SearchTrack> foundTracks = searchRepository.findTracks(input);
        return foundTracks;
    }
}
