package ca.wchang.openMoives.service;

import ca.wchang.openMoives.model.User;
import ca.wchang.openMoives.security.JwtUser;

public interface AuthService {
    Integer register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
