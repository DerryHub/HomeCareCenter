package edu.hust.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author chain
 * @date 2020/9/2
 **/
public class TraceIdUtil {

    public static final String TRACE_ID="traceId";

    /**
     * 获取一个新的traceId
     */
    public static String getOneTraceId(){
        return UUID.randomUUID().toString();
    }

    /**
     * 在当前上下文中设置指定的traceId，如果参数中的traceId为空则会
     * 重新指定一个traceId
     */
    public static void setTraceId(String traceId){
        if (StringUtils.isBlank(traceId)){
            traceId=getOneTraceId();
        }
        MDC.put(TRACE_ID,traceId);
    }

    /**
     * 在当前上下文中设置指定的traceId
     */
    public static void setTraceId(){
        MDC.put(TRACE_ID,getOneTraceId());
    }

    /**
     * 在当前上下文中获取traceId
     */
    public static String getTraceId(){
        return MDC.get(TRACE_ID);
    }

    /**
     * 在当前上下文中清除traceId
     */
    public static void clearTraceId(){
        MDC.remove(TRACE_ID);
    }

}
    