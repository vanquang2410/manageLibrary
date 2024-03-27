package com.example.manageLibrary.Services;

import org.springframework.stereotype.Service;

@Service
public class RespondObject {
    private String status ;
    private String message;
    private Object data ;

    public RespondObject() {
    }

    public RespondObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
