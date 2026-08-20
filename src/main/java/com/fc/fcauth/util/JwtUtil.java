package com.fc.fcauth.util;

import com.fc.fcauth.model.Employee;
import com.fc.fcauth.model.EmployeeRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

  private static final SecretKey SECRET_KEY =
      Jwts.SIG.HS256.key().build();

  private static final long EXPIRATION_TIME_MILLIS =
      1000L * 60 * 60;

  public static String createToken(Employee employee) {
    Date now = new Date();
    Date expireAt = new Date(now.getTime() + EXPIRATION_TIME_MILLIS);

    Set<String> roles = employee.getEmployeeRoles() == null
        ? Set.of()
        : employee.getEmployeeRoles().stream()
            .map(EmployeeRole::getName)
            .collect(Collectors.toSet());

    Map<String, Object> claims = new HashMap<>();
    claims.put("nickname", employee.getKakaoNickName());
    claims.put("roles", roles);

    return Jwts.builder()
        .subject(String.valueOf(employee.getId()))
        .claims(claims)
        .issuedAt(now)
        .expiration(expireAt)
        .signWith(SECRET_KEY)
        .compact();
  }

  public static Claims parseToken(String token) {
    return Jwts.parser()
        .verifyWith(SECRET_KEY)
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }
}