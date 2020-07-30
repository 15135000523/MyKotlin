package com.example.mykotlin.service;

public class BaseResponse<T> {
    private String errorCode;
    private String errorMsg;
    private T data;
    private boolean result;

    public String getCode() {
        return errorCode;
    }

    public void setCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return errorMsg;
    }

    public void setMessage(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
