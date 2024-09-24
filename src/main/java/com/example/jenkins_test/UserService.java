package com.example.jenkins_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(String oauthToken){
        WebClient webClient = WebClient.builder().build();
        String url = "https://kapi.kakao.com/v2/user/me";

        KakaoUserInfo userInfo = webClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + oauthToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .retrieve()
                .bodyToMono(KakaoUserInfo.class)
                .block();

        if (userInfo != null) {
            userInfo.setProvider("kakao");
        }else{
            throw new RuntimeException();
        }

        User user = new User(userInfo);

        userRepository.save(user);

        return;

    }

}
