package com.jdh.boot;

import com.google.gson.Gson;

public class Response {

    private String status = "";
    private String errorMessage = "";

    public Response(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String toJSON() {
        return new Gson().toJson(this);
    }
}
