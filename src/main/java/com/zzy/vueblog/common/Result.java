package com.zzy.vueblog.common;

import lombok.Data;

/**
 * @blog: blog.csdn.net/qq_43365046
 * @email zzz946041754@163.com
 * @description:
 * @author: zzy
 * @data: 2021/1/24-17:44
 **/
@Data
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result succ(Object data){
        return succ(200, "操作成功", data);
    }

    public static Result fail(String msg){
        return succ(500, msg, null);
    }

    public static Result fail(String msg, Object data){
        return succ(500, msg, data);
    }

    public static Result fail(int code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;

    }
    public static Result succ(int code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;

    }
}
