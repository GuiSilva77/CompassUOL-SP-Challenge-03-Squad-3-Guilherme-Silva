package br.com.compassuol.pb.challenge.msproducts.exceptions;

import java.util.Date;

public class ErrorResponse {
    private Date timestamp;
    private Integer status;
    private String message;
    private String description;


    public ErrorResponse(Date timestamp, Integer status, String message, String description) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
