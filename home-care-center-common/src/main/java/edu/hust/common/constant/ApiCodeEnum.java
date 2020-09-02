package edu.hust.common.constant;

import lombok.Getter;

/**
 * @author chain
 * @date 2020/9/2
 **/
@Getter
public enum ApiCodeEnum {
    // 状态码

    SUCCESS("", 0),
    FAILED("网络异常", 10000),
    PARAM_ERROR("接口参数错误", 10004),
    URL_NOT_EXIST("接口不存在", 10005),
    RPC_FAIL("调用服务异常", 10014),

    CALL_WRITE_ERROR("客户来电写操作异常", 20000),
    REMARK_WRITE_ERROR("客户跟进写操作异常", 20001),
    SHOWING_WRITE_ERROR("客户来访写操作异常", 20002),
    CUSTOMER_WRITE_ERROR("录入客户操作异常", 20003),
    DUPLICATE_DEMAND("该用户在该项目已存在需求，不可重复创建。", 20004),

    CUSTOMER_WILLING_PROJECT_EXISTS("该客源已被置业顾问%s录入，录入时间%s", 20005),

    NO_PERMISSION("没有权限访问", 20010),
    COMPLETE_PHONE_ERROR("补全手机号出错", 20012),
    NO_REQUIRED_PARAM("缺少必填参数", 20013),
    NO_RECOMMEND_ID("认领时查询客户信息参数为空", 20014),
    CLAIMING("您所认领的客户正在被别人认领，请确认", 20015),
    CLAIMED("您所认领的客户已经被认领，请确认", 20016),

    TRADE_ORDER_NOT_EXISTS("该房间暂未录入对应销售单，无法维护跟进", 20013),
    REMARK_FOLLOW_DATE_ILLEGAL("跟进时间不得晚于当前时间", 20015),
    REMARK_TRADE_SAVE_FAIL("交易信息异常，保存失败", 20014),
    REMARK_SAVE_FAIL("跟进信息保存失败", 20016),
    REMARK_DUE_DATE_ILLEGAL("应%s日期不能早于当前日期", 20017),
    TRADE_WORKBENCH_EXISTS("跟进已录入", 20018),

    PHONE_INVALID("手机号格式不正确", 20011);


    private int code;
    private String msg;

    ApiCodeEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
