package edu.hust.common.vo;

import lombok.Data;

@Data
public class ApiResult {
    private int code;
    private Object data;
    private String msg;

    public ApiResult() {
    }

    public ApiResult(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ApiResult(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static ApiResult buildSuccess(Object data){
        return new ApiResult(0, data);
    }

    public static ApiResult buildError(String msg){
        return new ApiResult(-1, "", msg);
    }

    public static ApiResult buildError(String msg, int code){
        return new ApiResult(code, "", msg);
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}

