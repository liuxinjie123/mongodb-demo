package com.mongodb.demo.model.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    public String code;
    public String msg;
    public T data;

    public Result(String code) {
        this.code = code;
    }

    public Result(String code, String msg) {
        this(code);
        this.msg = msg;
    }

    public Result(String code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public static Result setCode(String code) {
        return new Result(code);
    }

    public static Result setCodeMsg(String code, String msg) {
        return new Result(code, msg);
    }

    public static Result setCodeMsgData(String code, String msg, Object data) {
        return new Result(code, msg, data);
    }
}
