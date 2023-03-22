package ru.az.mz.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.az.mz.model.Role;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public final class JwtUtil {

    @Value("${security.secret}")
    private String secret;
    @Value("${security.authorization.header}")
    private String authorizationHeader;
    @Value("${security.authorization.expiration}")
    private long expiration;

    private final JwtUserService jwtUserService;


    public JwtUtil(JwtUserService jwtUserService) {
        this.jwtUserService = jwtUserService;
    }

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(
            String username,
            Set<Role> roles
    ) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", roles.stream().map(Role::getName).collect(Collectors.toSet()));
        Date now = new Date();
        Date validity = new Date(now.getTime() + expiration * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) throws JwtAuthenticationException {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid", HttpStatus.UNAUTHORIZED);
        }
    }

    public Authentication getAuthentication(String token) throws JwtAuthenticationException {
        UserDetails userDetails = jwtUserService.loadUserByUsername(getUsername(token));
        if (userDetails == null || !userDetails.isEnabled()) {
            throw new JwtAuthenticationException("User not found or not active", HttpStatus.UNAUTHORIZED);
        }
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader(authorizationHeader);
        return header != null
                ? request.getHeader(authorizationHeader).replaceAll("Bearer ", "")
                : null;
    }

}
