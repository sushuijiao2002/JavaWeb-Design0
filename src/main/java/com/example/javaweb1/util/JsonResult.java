package com.example.javaweb1.util;

import java.io.Serializable;


/*Json格式的数据进行响应
* 状态码、状态描述信息、数据。这部分功能封装到一个类中，将这类作为返回值，返回给前端浏览器*/
public class JsonResult<E> implements Serializable {
    private int status;  //状态码
    private String message; //描述信息
    private E data;  //任何的数据类型

    public JsonResult() {
        /*方便后期属性在不同场景下被调用*/
    }

    public JsonResult(int status) {
        this.status = status;
    }
    public JsonResult(Throwable e) {

        this.message = e.getMessage();
    }
    public JsonResult(int status, E data) {
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
