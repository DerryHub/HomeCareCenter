package edu.hust.start.interceptor;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.constant.JWTConst;
import edu.hust.common.constant.RoleEnum;
import edu.hust.common.util.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chain
 * @date 2020/9/4
 **/
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

    private JWTUtil jwtUtil=new JWTUtil();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String header=request.getHeader(JWTConst.AUTHORIZATION);

        if (header!=null&&!"".equals(header)){
            if (header.startsWith(JWTConst.BEARER)){

                String token=header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String role = (String) claims.get(JWTConst.ROLES);
                    if (role .equals(RoleEnum.ADMIN.getRole())) {
                        //为管理员
                        request.setAttribute(JWTConst.CLAIMS_ROLE,token);
                    }
                    if (role .equals(RoleEnum.NURSE.getRole())) {
                        request.setAttribute(JWTConst.CLAIMS_ROLE,token);
                    }
                    if (role .equals(RoleEnum.DOCTOR.getRole())) {
                        request.setAttribute(JWTConst.CLAIMS_ROLE,token);
                    }
                    return true;
                } catch (Exception e) {
                    throw new GlobalException(ApiCodeEnum.TOKEN_ERROR);
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
