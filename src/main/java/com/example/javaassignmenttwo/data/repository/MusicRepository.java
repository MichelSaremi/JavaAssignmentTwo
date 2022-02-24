package com.example.javaassignmenttwo.data.repository;

import com.example.javaassignmenttwo.model.Genre;
import com.example.javaassignmenttwo.model.Music;

import java.util.ArrayList;

public interface MusicRepository {
    ArrayList<Music> selectAllMusic();
    Music selectSpecificMusic(int TrackId);
    ArrayList<Genre> selectAllGenre();
}
