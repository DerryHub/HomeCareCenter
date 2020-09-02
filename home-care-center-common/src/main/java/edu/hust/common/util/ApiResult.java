package edu.hust.common.util;

public class ApiResult {
    private int code;
    private Object data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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

