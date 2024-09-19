package com.example.jenkins_test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

    @ResponseBody
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome jenkins";
    }
}
