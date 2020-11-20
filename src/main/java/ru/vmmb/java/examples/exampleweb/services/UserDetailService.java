package ru.vmmb.java.examples.exampleweb.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailService implements UserDetailsService {
    Map<String,UserDetails> users = new HashMap<>();
    {
        users.put("dba",new User("dba","$2y$12$WKisui341RQ8VYEM6UpnSujnFOsVUtMMCkHubKgm2YrtwH17lWtei"));
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return users.get(name);
    }

    static public class User implements UserDetails {

        private final String userName;
        private final String pswd;

        public User(String userNane, String pswd) {
            this.userName = userNane;
            this.pswd = pswd;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return pswd;
        }

        @Override
        public String getUsername() {
            return userName;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

}
