package com.example.javaassignmenttwo.view;


import com.example.javaassignmenttwo.data.services.MusicServiceImpl;
import com.example.javaassignmenttwo.data.services.SearchServiceImpl;
import com.example.javaassignmenttwo.model.Music;
import com.example.javaassignmenttwo.model.SearchTrack;
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
    private final SearchServiceImpl searchService;

    public HomeController(
            MusicServiceImpl musicService, SearchServiceImpl searchService ) {

        this.musicService = musicService;
        this.searchService = searchService;
    }
    @GetMapping("/")
    public String view(@ModelAttribute SearchTrack track,
            Model model
    ){
        Music music = new Music();
        model.addAttribute("artists", musicService.getFiveArtists());
        model.addAttribute("songs", musicService.getFiveSongs());
        model.addAttribute("genre", musicService.getFiveGenre());

        SearchTrack result = new SearchTrack(track.getTrackName(), "", "", "");
        model.addAttribute("searchTracks", result);

        return "home";
    }

    @PostMapping("/processSearch")
    public String searchForm(@ModelAttribute SearchTrack track, BindingResult errors, Model model)
    {
        if (errors.hasErrors())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        model.addAttribute("searchResults", searchService.getSearchedTracks(track.getTrackName()));
        return "searchresults";
    }
}
