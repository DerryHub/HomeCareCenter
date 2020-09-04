package edu.hust.common.constant;

/**
 * 角色枚举
 * @author chain
 * @date 2020/9/4
 **/
public enum  RoleEnum {


    DOCTOR("doctor"),

    CUSTOMER("customer"),

    ADMIN("admin"),

    NURSE("nurse");


    String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
