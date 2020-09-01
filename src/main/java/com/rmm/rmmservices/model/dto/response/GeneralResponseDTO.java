package com.rmm.rmmservices.model.dto.response;

/**
 * @author Paul Rodríguez-Ch
 */
public class GeneralResponseDTO<T> {
    private Boolean success;
    private String message;
    private T result;

    public GeneralResponseDTO(Boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
