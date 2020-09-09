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
    TOKEN_ERROR("token错误",10001),
    PARAM_ERROR("接口参数错误", 10004),
    URL_NOT_EXIST("接口不存在", 10005),
    RPC_FAIL("调用服务异常", 10014),

    CUSTOMER_WRITE_ERROR("录入客户操作异常", 20003),
    DUPLICATE_DEMAND("该用户在该项目已存在需求，不可重复创建。", 20004),

    NO_PERMISSION("没有权限访问", 20010),
    COMPLETE_PHONE_ERROR("补全手机号出错", 20012),
    NO_REQUIRED_PARAM("缺少必填参数", 20013),

    REMARK_SAVE_FAIL("跟进信息保存失败", 20016),
    REMARK_DUE_DATE_ILLEGAL("应%s日期不能早于当前日期", 20017),
    TRADE_WORKBENCH_EXISTS("跟进已录入", 20018),

    PHONE_INVALID("手机号格式不正确", 20011),

    IMAGE_OVERFLOW("图像超出设定大小", 30000),
    FAIL_TO_UPLOAD("上传失败", 30001),

    FAIL_TO_ADD("数据添加失败", 40001),
    FAIL_TO_UPDATE("数据修改失败", 40002),

    ID_is_NULL("用户名为空", 50001),
    PASSWORD_IS_NULL("密码为空", 50002),
    ID_OR_PSD_INCORRECT("用户名或密码错误", 50003),

    DANGER_ACTION_UNAUTHORIZATION("当前操作未授权",40000);

    private int code;
    private String msg;

    ApiCodeEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
