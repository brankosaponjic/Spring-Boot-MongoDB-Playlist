package brankosaponjic.playlist.controller;

import brankosaponjic.playlist.model.Song;
import brankosaponjic.playlist.service.SongService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/playlist")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping(value = {"", "/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/songs")
    public String getSongs(Model theModel) {
//        Collection<Song> songs = songService.findAll();
//        theModel.addAttribute("songs", songs);
//        return "songs/list-songs";
        // calling getPaginated() instead
        return getPaginated(1,theModel);
    }

    @GetMapping("/showSongFormForAdd")
    public String showFormForAdd(Model theModel) {
        Song theSong = new Song();
        theModel.addAttribute("song", theSong);
        return "songs/song-form";
    }

    @GetMapping("/showSongFormForUpdate")
    public String showFormForUpdate(@RequestParam("orderNo") int orderNo, Model theModel) {
        Song theSong = songService.findByOrderNo(orderNo).get();
        theModel.addAttribute("song", theSong);
        return "songs/song-form-update";
    }

    @GetMapping("/search")
    public String searchBy(@RequestParam("search") String search, Model theModel) {
        List<Song> theSongs = songService.searchBy(search);
        theModel.addAttribute("songs", theSongs);
        return "songs/list-songs";
    }

    @PostMapping("/save")
    public String saveSong(@Valid @ModelAttribute("song") Song song, BindingResult br) {
        if (br.hasErrors()) {
            return "songs/song-form";
        }
        songService.createSong(song);
        return "redirect:/playlist/songs";
    }

    @PostMapping("/update")
    public String updateSong(@RequestParam("orderNo") int orderNo,
                             @Valid @ModelAttribute("song") Song theSong,
                             BindingResult br) {
        if (br.hasErrors()) {
            return "songs/song-form-update";
        }
            songService.updateSong(orderNo, theSong);
        return "redirect:/playlist/songs";
    }

    @GetMapping("/delete")
    public String deleteSong(@RequestParam("songId") String id) {
        songService.deleteSong(id);
        return "redirect:/playlist/songs";
    }

    @GetMapping("/page/{pageNo}")
    public String getPaginated(@PathVariable("pageNo") int pageNo, Model model) {
        int pageSize = 8;

        Page<Song> page = songService.findPaginated(pageNo, pageSize);
        Collection<Song> songs = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("songs", songs);

        return "songs/list-songs";
    }
}