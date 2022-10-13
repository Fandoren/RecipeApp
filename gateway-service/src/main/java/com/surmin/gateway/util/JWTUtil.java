package com.surmin.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class JWTUtil {

    public static boolean hasRole(String token, List<String> roles) {
        byte[] decodedKey = "wjwhkZ9XFRwOkyTO47hLUykOfAVL6kyJGHr3J1fUNEaIhiIXQIBhT5JQE5Mq!fWv2FSzu9.75QNgxZ1Kr.Srpxm!cZn5vUssJ#CCf$Y6HwGft!fx.NE21JA8Hv6m#0ahTtjBePJvBsH4OX3V$yR040S1pHdJWm!aJxqt6qJ.kc2PMDpG71LhHeSJYlFqlI!RgfL@pEA@BugEK2D$TCBoKiR1a49YQl2pKY39p4jYl76D5n6bnHQgOSbdUMZWA4O7".getBytes();
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HMACSHA256");
        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        if(claims.get("roles") != null) {
            List<String> claimRoles = (List<String>) claims.get("roles");
            Set<String> result = roles.stream()
                    .distinct()
                    .filter(claimRoles::contains)
                    .collect(Collectors.toSet());
            if(result.size() != 0) {
                return true;
            }
        }
        return false;
    }

}
