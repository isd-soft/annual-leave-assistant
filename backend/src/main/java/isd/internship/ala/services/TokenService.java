package isd.internship.ala.services;

import io.jsonwebtoken.Claims;

public interface TokenService {
    boolean isAdmin(String header);
    long getId(String header);
    Claims parseToken(String header);
}
