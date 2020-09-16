package edu.hust.interceptor;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.constant.JWTConst;
import edu.hust.common.constant.RoleEnum;
import edu.hust.common.exception.GlobalException;
import edu.hust.common.util.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chain
 * @date 2020/9/4
 **/
@Service
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri=request.getRequestURI();
        if (uri!=null&&uri.contains("swa"))
            return true;

        String header=request.getHeader(JWTConst.AUTHORIZATION);

        if (header!=null&&!"".equals(header)){
            if (header.startsWith(JWTConst.BEARER)){

                String token=header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    if (!jwtUtil.isTokenValid(claims)){
                        unLoginError(response,"登录状态过期");
                        return false;
                    }
                    String role = (String) claims.get(JWTConst.ROLES);
                    String id= (String) claims.get(JWTConst.ID);
                    if (StringUtils.isEmpty(role)||StringUtils.isEmpty(id)){
                        unLoginError(response,"角色信息异常");
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
        unLoginError(response,"当前未登录");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


    private void  unLoginError(HttpServletResponse response,String msg){
        PrintWriter writer=null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        boolean fail=false;
        try {
            writer = response.getWriter();
            writer.print(msg);
        }catch (IOException e){
            fail=true;
            log.error("登录信息验证出错: {}, msg:{}",msg,e.getMessage());
            e.printStackTrace();
        }finally {
            if (!fail){
                log.error("登录信息验证出错: {}",msg);
            }
        }
    }


}
