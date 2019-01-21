package ca.wchang.openMoives.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface AuthorityMapper {
    ArrayList<Integer> findAuthorityById(Integer id);
}
