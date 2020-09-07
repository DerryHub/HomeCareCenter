package edu.hust.start.interceptor;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.constant.JWTConst;
import edu.hust.common.constant.RoleEnum;
import edu.hust.common.util.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String header=request.getHeader(JWTConst.AUTHORIZATION);

        if (header!=null&&!"".equals(header)){
            if (header.startsWith(JWTConst.BEARER)){

                String token=header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    if (!jwtUtil.isTokenValid(claims)){
                        return false;
                    }
                    String role = (String) claims.get(JWTConst.ROLES);
                    String id= (String) claims.get(JWTConst.ID);
                    if (StringUtils.isEmpty(role)||StringUtils.isEmpty(id)){
                        return false;
                    }
                    if (role .equals(RoleEnum.ADMIN.getRole())) {
                        //为管理员
                        request.setAttribute(JWTConst.CLAIMS_ROLE,JWTConst.CLAIMS_ADMIN);
                    }
                    if (role .equals(RoleEnum.NURSE.getRole())) {
                        request.setAttribute(JWTConst.CLAIMS_ROLE,JWTConst.CLAIMS_NURSE);
                    }
                    if (role .equals(RoleEnum.DOCTOR.getRole())) {
                        request.setAttribute(JWTConst.CLAIMS_ROLE,JWTConst.CLAIMS_DOCTOR);
                    }
                    request.setAttribute(JWTConst.ID,id);
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
