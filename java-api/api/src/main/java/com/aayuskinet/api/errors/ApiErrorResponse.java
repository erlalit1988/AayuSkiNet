package com.aayuskinet.api.errors;

public class ApiErrorResponse {
    private int statusCode;
    private String message;
    private String details;

    public ApiErrorResponse(int statusCode, String message, String details) {
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
    }

    // getters
    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}