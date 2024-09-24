package com.example.jenkins_test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OldKakaoUserInfo implements UserInfo{

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

    public OldKakaoUserInfo() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getProvider(){return "kakao";}

    @Override
    public String getName() {
        return profile.name;
    }

    @Override
    public String getEmail() {
        return profile.email;
    }

    @Override
    public String getPhone_number() {
        return profile.phone_number;
    }

    @Override
    public String getGender() {
        return profile.gender;
    }

    @Override
    public int getBirthyear() {
        return profile.birthyear;
    }


    @Override
    public String toString(){
        return  String.format("{id:%s, profile:{name:%s, email:%s, ph.n:%s, gender:%s, birthYear:%d}}",
                this.id, profile.name, profile.email, profile.phone_number, profile.gender, profile.birthyear);
    }
}
