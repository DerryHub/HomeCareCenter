package edu.hust.interceptor;

import edu.hust.common.exception.GlobalException;
import edu.hust.common.vo.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @author chain
 * @date 2020/9/2
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GlobalException.class)
    public ApiResult error(GlobalException e){
        e.printStackTrace();
        return new ApiResult(e.getError(), null, e.getMessage());
    }
}
