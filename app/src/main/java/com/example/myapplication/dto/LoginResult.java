package com.example.myapplication.dto;

public class LoginResult {
    private String result;
    private String mid;
    private String apiAccessKey;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getApiAccessKey() {
        return apiAccessKey;
    }

    public void setApiAccessKey(String apiAccessKey) {
        this.apiAccessKey = apiAccessKey;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "result='" + result + '\'' +
                ", mid='" + mid + '\'' +
                ", apiAccessKey='" + apiAccessKey + '\'' +
                '}';
    }
}
