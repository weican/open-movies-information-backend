package ca.wchang.openMoives.service;

import ca.wchang.openMoives.dao.UserMapper;
import ca.wchang.openMoives.model.User;
import ca.wchang.openMoives.security.JwtUser;
import ca.wchang.openMoives.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;
import static java.util.Arrays.sort;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private UserMapper userMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(
//            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            UserMapper userMapper) {
//        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userMapper = userMapper;
    }

    public Integer register(User userToAdd) {
        try {
            final String username = userToAdd.getUser_name();
            if (userMapper.findByUserName(username) != null) {
                return null;
            }
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            final String rawPassword = userToAdd.getPassword();
            userToAdd.setPassword(encoder.encode(rawPassword));
            userToAdd.setRoles(asList("ROLE_USER"));
            return userMapper.insert(userToAdd);
        }
        catch (AuthenticationException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String login(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
            System.out.println(upToken + "," +username +"," + password);

            final Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            final String token = jwtTokenUtil.generateToken(userDetails);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        ca.wchang.openMoives.security.JwtUser user = (ca.wchang.openMoives.security.JwtUser) userDetailsService.loadUserByUsername(username);
//        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
//            return jwtTokenUtil.refreshToken(token);
//        }
        return null;
    }

}
