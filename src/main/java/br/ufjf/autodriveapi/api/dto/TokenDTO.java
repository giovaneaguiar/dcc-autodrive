package br.ufjf.autodriveapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class TokenDTO {
    private String login;
    private String token;

    public TokenDTO() {} // construtor vazio

    public TokenDTO(String login, String token) {
        this.login = login;
        this.token = token;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
