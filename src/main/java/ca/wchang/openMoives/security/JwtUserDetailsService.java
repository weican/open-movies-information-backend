package ca.wchang.openMoives.security;

import ca.wchang.openMoives.dao.UserMapper;
import ca.wchang.openMoives.model.Authority;
import ca.wchang.openMoives.model.AuthorityName;
import ca.wchang.openMoives.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.findByUserName(userName);
        Authority auth = new Authority();
        auth.setId(2L);
        auth.setName(AuthorityName.ROLE_ADMIN);
//        auth.setUsers(2);
        List<Authority> list = new ArrayList<>();
        list.add(auth);
        user.setAuthorities(list);
        System.err.println( user.toString());
        if(user == null) {
            throw new  UsernameNotFoundException("No user found with user name" + userName);
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
