package edu.hust.common.exception;

import edu.hust.common.constant.ApiCodeEnum;

/**
 * 全局异常
 * @author chain
 * @date 2020/9/4
 **/
public class GlobalException extends RuntimeException {

    int error;

    public GlobalException(String message,int error) {
        super(message);
        this.error=error;
    }

    public int getError() {
        return error;
    }
    public GlobalException(ApiCodeEnum apiCodeEnum) {
        super(apiCodeEnum.getMsg());
        this.error=apiCodeEnum.getCode();
    }


}
