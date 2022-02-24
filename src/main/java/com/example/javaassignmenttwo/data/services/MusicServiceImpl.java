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

    //---generate an arraylist with five music objects
    public ArrayList<Music> getFiveMusic() {

        ArrayList<Music> alltracks = musicRepository.selectAllMusic();
        ArrayList<Music> fivetracks = new ArrayList<>();

        //---make sure artist name is there
        for (int i = 0; i < 5; i++) {
            int randomNumber = (ThreadLocalRandom.current().nextInt(1, alltracks.size() + 1));
            if (musicRepository.selectSpecificMusic(randomNumber).getArtistName() != null) {
                fivetracks.add(musicRepository.selectSpecificMusic(randomNumber));
            } else {
                i--;
            }
        }
        return fivetracks;
    }

    //---generate an arraylist of five genre objects
    public ArrayList<Genre> getFiveGenre() {

        ArrayList<Genre> allgenre = musicRepository.selectAllGenre();
        ArrayList<Genre> fivegenre = new ArrayList<>();

        //---make sure artist name is there
        for (int i = 0; i < 5; i++) {
            int randomNumber = (ThreadLocalRandom.current().nextInt(1, allgenre.size() + 1));
            fivegenre.add(CustomerRepositoryImpl.selectSpecificGenre(randomNumber));

        }
        return fivegenre;

    }


}
