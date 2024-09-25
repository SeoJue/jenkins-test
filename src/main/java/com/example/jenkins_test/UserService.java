package com.example.jenkins_test;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    //private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserService(/*UserRepository userRepository,*/ JwtUtils jwtUtils) {
        //this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    private User register(OauthAuthenticator authenticator, String oauthToken){

        Map<String, Object> attributes = authenticator.getUserData(oauthToken);

        User user = null;

        if (attributes != null) {
            user = new User(new KakaoUserInfo(attributes));
        }else{
            throw new RuntimeException();
        }

        //userRepository.save(user);

        return user;
    }

    public Map<String, String> login(String oauthToken, String provider){
        OauthAuthenticator authenticator = null;

        if(provider=="kakao"){
            authenticator = new KakaoAuthenticator();
        }

        //String username = provider + "_" + authenticator.verifyToken(oauthToken);

        //User user = userRepository.findByUsername(username);

        User user = null;

        if(user==null) {
            user = register(authenticator, oauthToken);
        }

        String accessToken = jwtUtils.createAccessToken(user.getUsername());
        String refreshToken = jwtUtils.createRefreshToken(user.getUsername());

        Map<String, String> tokens = new HashMap<>();

        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }
}
