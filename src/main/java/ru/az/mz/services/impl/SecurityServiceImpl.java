package ru.az.mz.services.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.az.mz.security.JwtUser;
import ru.az.mz.services.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {

    public String getUsername(){
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwtUser.getUsername();
    }

}
