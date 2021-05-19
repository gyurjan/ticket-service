package hu.otpmobil.partner.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
public class TechnicalUserHolder implements UserDetailsService {

    @Value("${hu.otpmobil.ticket.technical-user}")
    private String userName;

    @Value("${hu.otpmobil.ticket.technical-user-password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new User(userName, password, Collections.emptyList());
    }
}
