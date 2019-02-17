package ca.wchang.openMoives.dao;

import ca.wchang.openMoives.model.Movie_info;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface Movie_infoMapper {
    Movie_info getByTitle(String title);

    ArrayList<Movie_info> getAll(String orderBy);

    void insert(Movie_info info);

    void deleteAll();

    ArrayList<Integer> getAllMovieIdList();

    void updateBudgetAndRevenue(Movie_info info);
}
