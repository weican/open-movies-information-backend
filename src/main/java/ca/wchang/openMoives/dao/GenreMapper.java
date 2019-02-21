package ca.wchang.openMoives.dao;

import ca.wchang.openMoives.model.Genre;
import ca.wchang.openMoives.model.GenreDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface GenreMapper {
    ArrayList<GenreMapper> getByGenre_id(Integer id);
    void insert(Genre genre);
    void deleteByGenreId(Integer id);
    ArrayList<GenreMapper> getAll();
    void deleteAll();
    ArrayList<GenreDetail> getGenreList();
}
