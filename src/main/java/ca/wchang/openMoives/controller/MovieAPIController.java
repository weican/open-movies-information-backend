package ca.wchang.openMoives.controller;

import ca.wchang.openMoives.exception.MovieException;
import ca.wchang.openMoives.model.Genre;
import ca.wchang.openMoives.model.Movie_info;
import ca.wchang.openMoives.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieAPIController {

    @Autowired
    private MovieServiceImpl movieServiceImpl;

    @CrossOrigin
    @GetMapping("public/getGenre")
    public ResponseEntity<?> getGenre() {
        HashMap<Integer, List<Genre>> list = movieServiceImpl.getGenreList();
        if (list == null)
            throw new MovieException("Genre data are not found.");
        return new ResponseEntity<>( list, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("public/getAll")
    public ResponseEntity<?> getAll()  {
        ArrayList<Movie_info> list = movieServiceImpl.getAll();
        if(list == null)
            throw new MovieException("The movies data are not found.");
        return new ResponseEntity<ArrayList<Movie_info>>(list, HttpStatus.OK);

    }

    @GetMapping("/")
    public Map<String, String> greeting() {
        return new HashMap<String, String>() {{
            put("Message", "Welcome to open movies information service.");
        }};
    }

    @GetMapping("getMovies")
    public ResponseEntity<?> getMovies(@RequestParam String title) {

        Movie_info movie = movieServiceImpl.getList(title);
        if(movie == null)
            throw new MovieException("The movie is not found.");
        return new ResponseEntity<Movie_info>( movie, HttpStatus.OK);

    }

    @GetMapping("updateMovies")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, String> updateMovies(@RequestParam Integer year) {
        String message = movieServiceImpl.updateDBfromServer(year);
        if(message == null)
            throw new MovieException("Updating Database is failed.");
        return new HashMap<String, String>() {{
            put("Message", message);
        }};
    }



}
