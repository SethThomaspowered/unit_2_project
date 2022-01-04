package com.example.ultimateplaylist.model.Response;

import java.util.logging.Logger;

public class LoginResponse {
    private String jwt;
    private static final Logger LOGGER = Logger.getLogger(LoginResponse.class.getName());

    public LoginResponse(String jwt) {
        LOGGER.info("creating jwt");
        this.jwt = jwt;
    }

    public LoginResponse() {
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
