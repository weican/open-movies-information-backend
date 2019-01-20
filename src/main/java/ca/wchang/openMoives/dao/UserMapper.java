package ca.wchang.openMoives.dao;

import ca.wchang.openMoives.model.User;
import ca.wchang.openMoives.security.JwtUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUserName(String userName);
    Integer insert(User user);
}
