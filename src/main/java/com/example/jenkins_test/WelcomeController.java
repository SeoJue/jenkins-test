package com.example.jenkins_test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;


@Controller
public class WelcomeController {

    private final static String token = "MgeHKzUiHU6feW-viNEkX1D4Wd8C8Su-AAAAAQo8JCEAAAGSIibqIJIGkKnmukNu";

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome jenkins";
    }


    @ResponseBody
    @GetMapping("/test")
    public String test(){

        WebClient webClient = WebClient.builder().build();
        String url = "https://kapi.kakao.com/v2/user/me";

        // 어떤 HTTP 메소드로 요청 보낼지를 get(), post() 메소드 등으로 결정
        // 만일 다른 메소드를 쓰고 싶다면, method()
        String response = webClient.get() // webClient.method(HttpMethod.GET)
                .uri(url)	// 요청 경로 설정
                .header("Authorization", "Bearer " + token) // 요청 헤더 추가
                .header("Content-Type", "application/x-www-form-urlencoded")
                // body도 메소드에 따라 추가
                .retrieve()	// 여기 전까지가 요청을 정의 한 부분
                // 여기부터 정의하는건 응답을 어떻게 처리할 것인지
                .bodyToMono(String.class)	// 응답이 한번 돌아오고, 응답의 body를 String으로 해석
                .block();	// 동기식으로 처리
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(response);
            OldKakaoUserInfo userInfo = objectMapper.readValue(response, OldKakaoUserInfo.class);
            System.out.println(userInfo.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }

    @ResponseBody
    @GetMapping("/test2")
    public String test2() {
        WebClient webClient = WebClient.builder().build();
        String url = "https://kapi.kakao.com/v1/user/access_token_info";

        String response = webClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(response);

        return "success";
    }


    @GetMapping("/login")
    public ResponseEntity<?> login(){
        Map<String,String> tokens = userService.login(token, "kakao");

        return ResponseEntity.ok().body(tokens);
    }
}
