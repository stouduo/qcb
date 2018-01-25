package com.stouduo.qcb.util;

public class RestResult<T> {
    private int code;
    private String msg;
    private T data;

    public static RestResult ok(String msg) {
        return new RestResult().setCode(1).setMsg(msg);
    }

    public static <T> RestResult ok(String msg, T data) {
        return new RestResult().setCode(1).setMsg(msg).setData(data);
    }

    public static <T> RestResult ok(T data) {
        return new RestResult().setCode(1).setData(data);
    }

    public static RestResult error(String msg) {
        return new RestResult().setCode(0).setMsg(msg);
    }

    public static <T> RestResult error(String msg, T data) {
        return new RestResult().setCode(0).setMsg(msg).setData(data);
    }

    public String getMsg() {
        return msg;
    }

    public RestResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public RestResult setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public RestResult setCode(int code) {
        this.code = code;
        return this;
    }
}
