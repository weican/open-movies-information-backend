package ca.wchang.openMoives.controller;

import ca.wchang.openMoives.model.Movie_info;
import ca.wchang.openMoives.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class movieController {

    @Autowired
    private MovieService movieService;


    @GetMapping("/")
    public String greeting() {
        return "Welcome to open movies information service.";
    }


    @GetMapping("getMovies")
    public ResponseEntity<?> getMovies(@RequestParam String title) {
        try {
            Movie_info movie = movieService.getList(title);
            if(movie == null) return new ResponseEntity<String>( "Not found", HttpStatus.OK);
            return new ResponseEntity<Movie_info>( movie, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>( "Not found", HttpStatus.OK);
        }
    }

    @CrossOrigin
    @GetMapping("getAll")
    public ResponseEntity<?> getAll()  {
        try {
            ArrayList<Movie_info> list = movieService.getAll();
            if(list == null) return new ResponseEntity<String>( "Not found", HttpStatus.OK);
            return new ResponseEntity<ArrayList<Movie_info>>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>( "Not found", HttpStatus.OK);
        }
    }

    @GetMapping("updateMovies")
    public ResponseEntity<?> updateMovies(@RequestParam Integer year) {
        try {
            return new ResponseEntity<String>( movieService.updateDBfromServer(year), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>( "Error", HttpStatus.OK);
        }

    }

    @CrossOrigin
    @GetMapping("getGenre")
    public ResponseEntity<?> getGenre() {
        try {
            return new ResponseEntity<>( movieService.getGenreList(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>( "Error", HttpStatus.OK);
        }
    }

}
