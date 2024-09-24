package com.example.jenkins_test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUserInfo{

    private String provider;
    private String id;
    @JsonProperty("kakao_account")
    private Profile profile;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private class Profile{
        private String name;
        private String email;
        private String phone_number;
        private String gender;
        private int birthyear;

        public Profile() {
        }
    }

    public String getName() {
        return profile.name;
    }

    public String getEmail() {
        return profile.email;
    }

    public String getPhone_number() {
        return profile.phone_number;
    }

    public String getGender() {
        return profile.gender;
    }

    public int getBirthyear() {
        return profile.birthyear;
    }

    public KakaoUserInfo() {
    }

    public String getId() {
        return id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider){
        this.provider = provider;
    }

    @Override
    public String toString(){
        return  String.format("{id:%s, profile:{name:%s, email:%s, ph.n:%s, gender:%s, birthYear:%d}}",
                this.id, profile.name, profile.email, profile.phone_number, profile.gender, profile.birthyear);
    }
}
