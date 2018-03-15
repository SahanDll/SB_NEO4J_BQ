package com.dev.au.service.impl;


import com.dev.au.util.Common;
import com.dev.au.util.KeyGenerator;
import com.dev.db.data.h2.bean.UserLogin;
import com.dev.db.data.h2.repository.UserLoginRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthUserDetailsService implements UserDetailsService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AuthUserDetailsService.class);
    @Autowired
    UserLoginRepository repository;

/*    @Value("${security.user.name}")
    private String username;

    @Value("${security.user.password}")
    private String password;

    @Value("${security.user.role}")
    private String role;*/

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserLogin user = repository.findByUserName(s);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        switch (user.getUserRole()){
            case 1:
                authorities.add(new SimpleGrantedAuthority("SUPER"));
                break;
            case 2:
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
                break;
            case 3:
                authorities.add(new SimpleGrantedAuthority("MAINTAIN"));
                break;
            case 4:
                authorities.add(new SimpleGrantedAuthority("MONITOR"));
                break;
            case 5:
                authorities.add(new SimpleGrantedAuthority("GUEST"));
                break;
            case 6:
                authorities.add(new SimpleGrantedAuthority("JILL"));
                break;
            case 7:
                authorities.add(new SimpleGrantedAuthority("JILLADMIN"));
                break;

        }
        UserDetails userDetails = null;
        try {
            userDetails = new org.springframework.security.core.userdetails.
                    User(user.getUserName(), Common.getInstance().getUserADMap().get(user.getUserName().toUpperCase()), authorities);
        } catch (Exception e) {
            logger.error("Auth Error : ", e);
        }
/*        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUserName(), user.getPassword(), authorities);*/

        return userDetails;
    }

    //POST http://localhost:8381/fmt-sentinel/oauth/token
    //Authorization: Basic sentinelJwtClientId:mySecret //base64 encoded
    //Content-Type : application/x-www-form-urlencoded

    //request body  > grant_type=password&username=maybank&password=secret@123

}
