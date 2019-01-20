package ca.wchang.openMoives.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class User {
        private Long id;
        private Integer role_id;
        private String username;
        private String password;
        private String email;
        private List<String> roles;
        private boolean enabled;
        private Date lastdasswordresetdate;
        private List<Authority> authorities;
        private String firstname;
        private String lastname;

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Date getLastdasswordresetdate() {
        return lastdasswordresetdate;
    }

    public void setLastdasswordresetdate(Date lastdasswordresetdate) {
        this.lastdasswordresetdate = lastdasswordresetdate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

        public Long getId() {
            return id;
        }

        public String getUser_name() {
            return username;
        }

        public void setUser_name(String user_name) {
            this.username = user_name;
        }

        public boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public List<Authority> getAuthorities() {
            return authorities;
        }

        public void setAuthorities(List<Authority> authorities) {
            this.authorities = authorities;
        }

        public Integer getRole_id() {
            return role_id;
        }

        public void setRole_id(Integer role_id) {
            this.role_id = role_id;
        }

        public Integer getRole() {
            return role;
        }

        public void setRole(Integer role) {
            this.role = role;
        }

        private Integer role;

        public Integer getRoleID() {
            return role_id;
        }

        public void setRoleID(Integer roleID) {
            this.role_id = roleID;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }


        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        public Date getLastPasswordResetDate() {
            return lastdasswordresetdate;
        }

        public void setLastPasswordResetDate(Date lastPasswordResetDate) {
            this.lastdasswordresetdate = lastPasswordResetDate;
        }

    @Override
    public String toString() {
        return "userName: " + username +", password: "+ password +
                ",email: " + email + ",lastPasswordResetDate: " + lastdasswordresetdate +
                ",authorities: " + authorities;
    }
    }
