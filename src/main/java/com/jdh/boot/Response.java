package com.jdh.boot;

import com.google.gson.Gson;

public class Response {

    private String status = "";
    private String message = "";

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String toJSON() {
        return new Gson().toJson(this);
    }
}
