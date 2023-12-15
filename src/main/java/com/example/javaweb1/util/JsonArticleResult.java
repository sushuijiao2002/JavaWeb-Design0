package com.example.javaweb1.util;


import java.io.Serializable;

public class JsonArticleResult<E> implements Serializable {
    private int status;  //状态码
    private String message; //描述信息
    private E data;  //数据

    public JsonArticleResult() {
    }

    public JsonArticleResult(int status) {
        this.status = status;
    }
    public JsonArticleResult(Throwable e) {
        this.message = e.getMessage();
    }
    public JsonArticleResult(int status, E data) {
        this.status = status;
        this.data = data;
    }

    public int getstatus() {
        return status;
    }

    public void setstatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}

