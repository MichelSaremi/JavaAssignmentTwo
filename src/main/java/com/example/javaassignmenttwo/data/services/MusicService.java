package com.example.javaassignmenttwo.data.services;

import com.example.javaassignmenttwo.model.Music;

import java.util.ArrayList;

public interface MusicService {
    ArrayList<String> getFiveArtists();
    ArrayList<String> getFiveSongs();
    ArrayList<String> getFiveGenre();
}
