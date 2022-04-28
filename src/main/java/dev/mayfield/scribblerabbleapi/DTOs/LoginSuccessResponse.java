package dev.mayfield.scribblerabbleapi.DTOs;

public class LoginSuccessResponse {
    private String username;
    private String token;
    private String redirect;

    public LoginSuccessResponse() {
    }

    public LoginSuccessResponse(String username, String token, String redirect) {
        this.username = username;
        this.token = token;
        this.redirect = redirect;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    @Override
    public String toString() {
        return "LoginSuccessResponse [username=" + username + ", token=" + token + "]";
    }
}
