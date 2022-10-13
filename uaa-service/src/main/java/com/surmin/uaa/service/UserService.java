package com.surmin.uaa.service;

import com.surmin.uaa.exception.EntityNotFoundException;
import com.surmin.uaa.model.User;
import com.surmin.uaa.model.UserAuthorization;
import com.surmin.uaa.model.UserDto;
import com.surmin.uaa.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.nio.CharBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    private final String USER_DTO_NOT_PROVIDED =  "User dto wasn't provided";
    private final String USER_ID_SHOULD_NOT_BE_SPECIFIED = "User Id should not be manually specified";
    private final String USER_DTO_ID_CANT_BE_NULL = "User dto id can't be set to null";
    private final String MONGO_USER_NOT_FOUND = "User was not found in mongoDB";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private SecretKey secretKey;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    protected void init() throws NoSuchAlgorithmException {
        byte[] decodedKey = "wjwhkZ9XFRwOkyTO47hLUykOfAVL6kyJGHr3J1fUNEaIhiIXQIBhT5JQE5Mq!fWv2FSzu9.75QNgxZ1Kr.Srpxm!cZn5vUssJ#CCf$Y6HwGft!fx.NE21JA8Hv6m#0ahTtjBePJvBsH4OX3V$yR040S1pHdJWm!aJxqt6qJ.kc2PMDpG71LhHeSJYlFqlI!RgfL@pEA@BugEK2D$TCBoKiR1a49YQl2pKY39p4jYl76D5n6bnHQgOSbdUMZWA4O7".getBytes();
        secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HMACSHA256");
    }

    public UserDto findOne(String entityId) {
        User user = userRepository.findById(entityId).orElse(null);
        return user == null ? null : new UserDto(user);
    }

    public Collection<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    public UserDto save(UserDto userDto) {
        if(userDto == null) {
            throw new EntityNotFoundException(USER_DTO_NOT_PROVIDED);
        }
        if(userDto.getEntityId() != null) {
            throw new IllegalArgumentException(USER_ID_SHOULD_NOT_BE_SPECIFIED);
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return new UserDto(userRepository.save(userDto.toUser()));
    }

    public UserDto update(UserDto userDto) {
        if(userDto == null) {
            throw new EntityNotFoundException(USER_DTO_NOT_PROVIDED);
        }
        if(userDto.getEntityId() == null) {
            throw new IllegalArgumentException(USER_DTO_ID_CANT_BE_NULL);
        }
        userRepository.findById(userDto.getEntityId())
                .orElseThrow(() -> new EntityNotFoundException(MONGO_USER_NOT_FOUND));
        User user = userDto.toUser();
        return new UserDto(userRepository.save(user));
    }

    public void delete(UserDto userDto) {
        if(userDto == null) {
            throw new IllegalArgumentException(USER_DTO_NOT_PROVIDED);
        }
        User user = userRepository.findById(userDto.getEntityId())
                .orElseThrow(() -> new EntityNotFoundException(MONGO_USER_NOT_FOUND));
        userRepository.delete(user);
    }

    public UserAuthorization signIn(UserDto userDto) {
        var user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(MONGO_USER_NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(userDto.getPassword()), user.getPassword())) {
            return new UserAuthorization(user, createToken(user));
        }

        throw new IllegalArgumentException("Invalid password");
    }

    public UserAuthorization validateToken(String token) {
        String username = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException(MONGO_USER_NOT_FOUND);
        }

        User user = userOptional.get();
        return new UserAuthorization(user, createToken(user));
    }

    private String createToken(User user) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("roles", user.getRoles());
        Claims claims = Jwts.claims(claimsMap).setSubject(user.getUsername());

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey)
                .compact();
    }
}
