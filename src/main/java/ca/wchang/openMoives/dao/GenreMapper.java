package ca.wchang.openMoives.dao;

import ca.wchang.openMoives.model.Genre;
import ca.wchang.openMoives.model.GenreDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface GenreMapper {
    public ArrayList<GenreMapper> getByGenre_id(Integer id);
    public void insert(Genre genre);
    public void deleteByGenreId(Integer id);
    public ArrayList<GenreMapper> getAll();
    public void deleteAll();
    public ArrayList<GenreDetail> getGenreList();
}
