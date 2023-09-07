package com.nantian.uccp.utils;


import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ResponseBody implements Serializable{
    private static final long serialVersionUID = 1886106011131539131L;

    private String code;
    private String message;
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
    private Object data;

    /* set and get */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /* 构造 */
    public ResponseBody() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResponseBody(String code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 默认成功或者失败,无数据
     * @param resultCode
     */
    public ResponseBody(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    /**
     * 枚举结果以及获取数据
     * @param resultCode
     * @param
     */
    public ResponseBody(ResultCode resultCode,String message) {
        this.code = resultCode.getCode();
        this.message = message;
    }

    /**
     * 枚举结果以及获取数据
     * @param resultCode
     * @param data
     */
    public ResponseBody(ResultCode resultCode,Object data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
    public enum ResultCode {
        /* 成功 */
        SUCCESS("200", "Success!"),
        /* 失败 */
        FAIL("400", "Failure!"),

        /* 参考HTTP状态码 */
        NO_PERMISSION("403", "Need Authorities!"),//没有权限
        LOGIN_NO("402", "Need Login!"),//未登录
        LOGIN_FAIL("401", "Login Failure!"),//登录失败
        LOGIN_SUCCESS("200", "Login Success!"),//登录成功
        LOGOUT_SUCCESS("200", "Logout Success!"),//退出登录
        SESSION_EXPIRES("101", "Session Expires!"),//会话到期
        SESSION_EXPIRES_OTHER_LOGIN("101", "Session Expires!Other users login！");//会话到期,其他用户登录


        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        /**
         *
         * @param code
         * @param message
         */
        ResultCode(String code, String message) {
            this.code = code;
            this.message = message;
        }

        }
}
