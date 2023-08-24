package com.example.myapplication.dto;

public class WriteResult {
    private String result;
    private int bno;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    @Override
    public String toString() {
        return "WriteResult{" +
                "result='" + result + '\'' +
                ", bno=" + bno +
                '}';
    }
}
