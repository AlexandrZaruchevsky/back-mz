package ru.az.mz.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.az.mz.services.UserServiceV1V1;
import ru.az.mz.services.MyException;

@Service
public final class JwtUserService implements UserDetailsService {

    private final UserServiceV1V1 userServiceV1;

    public JwtUserService(UserServiceV1V1 userServiceV1) {
        this.userServiceV1 = userServiceV1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return new JwtUser(userServiceV1.findByUsername(username));
        } catch (MyException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

}
