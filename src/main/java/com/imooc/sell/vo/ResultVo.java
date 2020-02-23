package com.imooc.sell.vo;

import lombok.Data;

@Data
public class ResultVo<T> {
    private Integer code;
    private String msg;
    private T data;


    public ResultVo() {
    }

    public ResultVo(T data) {
        this.data = data;
    }

    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultVo success(Object obj){
        return new ResultVo(0, "成功", obj);
    }

    public static ResultVo error(Integer code, String msg){
        return new ResultVo(code, msg);
    }
}
