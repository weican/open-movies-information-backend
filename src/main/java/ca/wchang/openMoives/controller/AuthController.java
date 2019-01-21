package ca.wchang.openMoives.controller;

import ca.wchang.openMoives.exception.AuthenticationException;
import ca.wchang.openMoives.model.User;
import ca.wchang.openMoives.security.JwtUser;
import ca.wchang.openMoives.security.JwtAuthenticationRequest;
import ca.wchang.openMoives.security.JwtAuthenticationResponse;
import ca.wchang.openMoives.security.JwtTokenUtil;
import ca.wchang.openMoives.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
//    @Qualifier("JwtUserDetailsService")
    private UserDetailsService userDetailsService;

//    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
//    @PostMapping("auth")
//    public ResponseEntity<?> createAuthenticationToken(
//            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
//        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//
//        // Return the tokenauthController
//        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
//    }

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
            throws AuthenticationException {

        System.out.println(authenticationRequest.getUsername());
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Reload password post-security so we can generate the token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

//    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
//    public ResponseEntity<?> refreshAndGetAuthenticationToken(
//            HttpServletRequest request) throws AuthenticationException{
//        String token = request.getHeader(tokenHeader);
//        String refreshedToken = authService.refresh(token);
//        if(refreshedToken == null) {
//            return ResponseEntity.badRequest().body(null);
//        } else {
//            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
//        }
//    }

    @PostMapping("/auth/register")
    public HashMap<String, String> register(@RequestBody User addedUser) {
        if(authService.register(addedUser) != null)
            return new HashMap<String,String>() {{put("Message", "Success" );}};
        else return new HashMap<String,String>() {{put("Message", "Oops...something wrong happened." );}};
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /**
     * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
     */
    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }

}
