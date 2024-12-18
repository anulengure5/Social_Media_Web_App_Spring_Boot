package com.anulengure5.websocialapp.Response;

public class ApiResponse {

    private String message;

    private boolean Status;


    public ApiResponse() {
    }

    public ApiResponse( String message, boolean status) {
        Status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
