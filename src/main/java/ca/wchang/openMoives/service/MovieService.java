package ca.wchang.openMoives.service;

import ca.wchang.openMoives.model.Genre;

import java.util.HashMap;
import java.util.List;

public interface MovieService {

    void updateBudgetAndRevenueFrmServer(Integer movie_id);
    HashMap<Integer,String> getGenres();
    String updateDBfromServer(Integer  year);
    HashMap<Integer, List<Genre>>  getGenreList();
}
