package ca.wchang.openMoives.service;

import ca.wchang.openMoives.model.Genre;
import ca.wchang.openMoives.model.Movie_info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface MovieService {

    Movie_info getList(String title);
    ArrayList<Movie_info> getAll();
    void updateBudgetAndRevenueFrmServer(Integer movie_id);
    HashMap<Integer,String> getGenres();
    String updateDBfromServer(Integer  year);
    HashMap<Integer, List<Genre>>  getGenreList();
}
