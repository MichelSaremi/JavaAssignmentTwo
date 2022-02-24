package com.example.javaassignmenttwo.data.services;

import com.example.javaassignmenttwo.data.repository.CustomerRepositoryImpl;
import com.example.javaassignmenttwo.data.repository.MusicRepositoryImpl;
import com.example.javaassignmenttwo.model.Genre;
import com.example.javaassignmenttwo.model.Music;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MusicServiceImpl implements MusicService{
    private final MusicRepositoryImpl musicRepository;

    public MusicServiceImpl(
            MusicRepositoryImpl musicRepository
    ) {
        this.musicRepository = musicRepository;
    }

    //---generate an arraylist with five artist names
    public ArrayList<String> getFiveArtists() {

        ArrayList<Music> alltracks = musicRepository.selectAllMusic();
        ArrayList<String> fiveArtists = new ArrayList<>();

        //---make sure artist name is there
        for (int i = 0; i < 5; i++) {
            int randomNumber = (ThreadLocalRandom.current().nextInt(1, alltracks.size() + 1));
            if (musicRepository.selectSpecificMusic(randomNumber).getArtistName() != null && !fiveArtists.contains(musicRepository.selectSpecificMusic(randomNumber).getArtistName())) {
                fiveArtists.add(musicRepository.selectSpecificMusic(randomNumber).getArtistName());
            } else {
                i--;
            }
        }
        return fiveArtists;
    }

    //---generate an arraylist with five song names
    public ArrayList<String> getFiveSongs() {

        ArrayList<Music> alltracks = musicRepository.selectAllMusic();
        ArrayList<String> fiveSongs = new ArrayList<>();

        //---make sure artist name is there
        for (int i = 0; i < 5; i++) {
            int randomNumber = (ThreadLocalRandom.current().nextInt(1, alltracks.size() + 1));
            if (!fiveSongs.contains(musicRepository.selectSpecificMusic(randomNumber).getSongName())) {
                fiveSongs.add(musicRepository.selectSpecificMusic(randomNumber).getSongName());
            } else {
                i--;
            }
        }
        return fiveSongs;
    }


    //---generate an arraylist of five genre objects
    public ArrayList<String> getFiveGenre() {

        ArrayList<Genre> allgenre = musicRepository.selectAllGenre();
        ArrayList<String> fivegenre = new ArrayList<>();

        //---make sure artist name is there
        for (int i = 0; i < 5; i++) {
            int randomNumber = (ThreadLocalRandom.current().nextInt(1, allgenre.size() + 1));
            if(!fivegenre.contains(CustomerRepositoryImpl.selectSpecificGenre(randomNumber).getName())) {
                fivegenre.add(CustomerRepositoryImpl.selectSpecificGenre(randomNumber).getName());
            }else{
                i--;
            }
        }
        return fivegenre;

    }


}
