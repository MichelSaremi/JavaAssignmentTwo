package com.example.javaassignmenttwo.data.services;

import com.example.javaassignmenttwo.model.Genre;
import com.example.javaassignmenttwo.model.Music;

import java.util.ArrayList;

public interface MusicService {
    ArrayList<Music> getFiveMusic();
    ArrayList<Genre> getFiveGenre();
}
