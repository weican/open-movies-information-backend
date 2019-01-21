package ca.wchang.openMoives.security;

import ca.wchang.openMoives.dao.AuthorityMapper;
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

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.findByUserName(userName);
        ArrayList<Integer> authList = authorityMapper.findAuthorityById(user.getId().intValue());
        AuthorityName[] authorityName = AuthorityName.values();
        List<Authority> list = new ArrayList<>();
        for(Integer i = 0; i < authList.size(); i++) {
            Authority auth = new Authority();
            auth.setId(Long.valueOf(authList.get(i)));
            auth.setName(authorityName[authList.get(i)-1]);
            list.add(auth);
        }
        user.setAuthorities(list);
        if(user == null) {
            throw new  UsernameNotFoundException("No user found with user name" + userName);
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
