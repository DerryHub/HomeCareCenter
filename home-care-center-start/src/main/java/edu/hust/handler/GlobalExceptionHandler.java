package edu.hust.handler;

import edu.hust.common.exception.GlobalException;
import edu.hust.common.vo.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * @author chain
 * @date 2020/9/2
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GlobalException.class)
    public ApiResult handlerError(HttpServletRequest request, GlobalException e){
        log.error("ERROR:"+request.getRequestURI()+e.getMessage());
        e.printStackTrace();
        return new ApiResult(e.getError(), null, e.getMessage());
    }

}
