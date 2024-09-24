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

    public Map<String, String> register(String oauthToken){
        WebClient webClient = WebClient.builder().build();
        String url = "https://kapi.kakao.com/v2/user/me";

         Map<String, Object> attributes = webClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + oauthToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        System.out.println(attributes);

        User user = null;

        if (attributes != null) {
            user = new User(new KakaoUserInfo(attributes));
        }else{
            throw new RuntimeException();
        }

        //userRepository.save(user);

        String accessToken = jwtUtils.createAccessToken(user.getUsername());
        String refreshToken = jwtUtils.createRefreshToken(user.getUsername());

        Map<String, String> tokens = new HashMap<>();

        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

}
