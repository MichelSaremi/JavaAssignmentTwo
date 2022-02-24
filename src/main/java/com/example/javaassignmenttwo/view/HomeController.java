package com.example.javaassignmenttwo.view;


import com.example.javaassignmenttwo.data.services.MusicServiceImpl;
import com.example.javaassignmenttwo.model.Music;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class HomeController {
    private final MusicServiceImpl musicService;

    public HomeController(
            MusicServiceImpl musicService ) {

        this.musicService = musicService;
    }
    @GetMapping("/")
    public String view(
            Model model
    ){
        Music music = new Music();
        model.addAttribute("artists", musicService.getFiveArtists());
        model.addAttribute("songs", musicService.getFiveSongs());
        model.addAttribute("genre", musicService.getFiveGenre());
        return "home";
    }

    @PostMapping("/")
    public String form(
        @ModelAttribute Music music,
        BindingResult errors,
        Model model
    ){
        if(errors.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return null;
    }


}
