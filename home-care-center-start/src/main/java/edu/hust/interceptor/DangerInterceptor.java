package edu.hust.interceptor;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.constant.JWTConst;
import edu.hust.common.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 安全相关过滤器
 * 对于一键初始化操作拦截
 * @author chain
 * @date 2020/9/9
 **/
@Slf4j
public class DangerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String role= (String) request.getAttribute(JWTConst.CLAIMS_ROLE);
        if (role==null|| !role.equals(JWTConst.CLAIMS_ADMIN)){
            throw new GlobalException(ApiCodeEnum.DANGER_ACTION_UNAUTHORIZED);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String id= (String) request.getAttribute(JWTConst.ID);
        log.info("ID: {} 执行了危险操作",id);
    }
}
