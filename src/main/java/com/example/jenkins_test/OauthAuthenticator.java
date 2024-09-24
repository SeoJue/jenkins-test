package com.example.jenkins_test;

import java.util.Map;

public interface OauthAuthenticator {

    public Map<String, Object> getUserData(String accessToken);

    public String verifyToken(String accessToken);
}
