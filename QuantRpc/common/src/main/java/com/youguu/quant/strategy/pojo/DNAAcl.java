package com.youguu.quant.strategy.pojo;

/**
 * Created by lenovo on 2017/4/14.
 */
public class DNAAcl {
    private boolean permissionFlag;
    private String describe;
    /**
     * 1 用户拥有机器人使用权限
     * 2 用户拥有免费权限
     * 3 用户拥有分享的权限
     * -2 免费权限已经使用
     * -3 分享权限已经使用
     */
    private int status;

    public boolean isPermissionFlag() {
        return permissionFlag;
    }

    public void setPermissionFlag(boolean permissionFlag) {
        this.permissionFlag = permissionFlag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DNAAcl{" +
                "permissionFlag=" + permissionFlag +
                ", describe='" + describe + '\'' +
                ", status=" + status +
                '}';
    }
}
