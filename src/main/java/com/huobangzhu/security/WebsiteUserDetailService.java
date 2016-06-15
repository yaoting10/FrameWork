package com.huobangzhu.security;

import com.google.common.collect.Lists;
import com.huobangzhu.util.ErrorCode;
import com.huobangzhu.util.RespondableException;
import com.huobangzhu.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * user:yt
 * date:2014-11-10
 */
@Component
public class WebsiteUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userNumber) throws UsernameNotFoundException {

        com.huobangzhu.core.domain.User consumer = userService.findByUserNumber(userNumber);
        if(Objects.isNull(consumer)){
            throw new RespondableException(ErrorCode.NO_SUCH_USER, "Unable to find consumer " + userNumber);
        }
        List authorities = Lists.newArrayList();
        SimpleGrantedAuthority authority;
        if(consumer.getUserType() ==1){
            authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        }else {
            authority = new SimpleGrantedAuthority("ROLE_USER");
        }
        authorities.add(authority);
        User user = new User(consumer.getUserNumber(), consumer.getPassword(), true, true, true, true, authorities);
        return user;
    }
}
