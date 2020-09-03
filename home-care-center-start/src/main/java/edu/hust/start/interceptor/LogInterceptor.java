package edu.hust.start.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author chain
 * @date 2020/9/3
 **/

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    private static final String TRACE_ID = "traceId";

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse reponse, Object arg2, Exception arg3)
            throws Exception {
        MDC.clear();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
            throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        String traceId = request.getHeader(TRACE_ID);
        if (StringUtils.isBlank(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        if (StringUtils.isNotBlank(traceId)) {
            MDC.put(TRACE_ID, traceId);

        }
        String uri = request.getRequestURI();
        log.info("preHandle uri={}", uri);
        return true;
    }


}
