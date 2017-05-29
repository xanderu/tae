package com.cristian.domain;

/**
 * Created by cristian.petre on 5/28/17.
 */
public class Response {
    private String response;

    public Response(String response) {
        this.response = response;
    }

    public Response() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
