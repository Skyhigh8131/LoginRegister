package com.example.loginregister;

public class UserAccount
{
    private String idToken; //Firebase Uid (고유 토큰 정보)
    private String Email; //이메일
    private String Hackbun; //학번

    public UserAccount() { }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmail() { return Email; }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getHackbun() {
        return Hackbun;
    }

    public void setHackbun(String hackbun) {
        Hackbun = hackbun;
    }

}
