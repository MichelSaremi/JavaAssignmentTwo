package com.example.javaassignmenttwo.data.services;

import com.example.javaassignmenttwo.data.repository.SearchRepository;
import com.example.javaassignmenttwo.model.CustomerCountry;
import com.example.javaassignmenttwo.model.SearchTrack;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class SearchServiceImpl implements SearchService
{
    private final SearchRepository searchRepository;

    public SearchServiceImpl(
            SearchRepository searchRepository
    ) {
        this.searchRepository = searchRepository;
    }

    public ArrayList<SearchTrack> getSearchedTracks(String input)
    {
        String output = null;
        ArrayList<String> outputs = new ArrayList<String>();
        ArrayList<SearchTrack> foundTracks = searchRepository.findTracks(input);

        if(foundTracks.size() != 0) {
            for (SearchTrack t : foundTracks) {
                output=
                        t.getTrackName() + ", "+
                                t.getArtist() + ", "+
                                t.getAlbum() + ", "+
                                t.getGenre()+". ";
                outputs.add(output);
            }}
        return foundTracks;
    }
}
