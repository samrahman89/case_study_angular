package com.abdul.library.books.security;

public class AuthenticationDto {
    private Boolean login = Boolean.FALSE;
    private String username;

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
