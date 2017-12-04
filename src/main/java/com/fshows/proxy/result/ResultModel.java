/**
 * Copyright (c) 2016, 791650277@qq.com(Mr.kiwi) All Rights Reserved.
 */
package com.fshows.proxy.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fshows.proxy.contants.ErrorConstants;

import java.io.Serializable;

/**
 * 项目：liquidator-platform
 * 包名：com.fshows.liquidator.platform.common.result
 * 功能：返回结果对象
 * 时间：2016-07-28 10:00
 * 作者：Mr.Kiwi
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResultModel<T> implements Serializable {

    private static final long serialVersionUID = -9204659000311373130L;
    private boolean isSuccess;
    private T returnValue;
    private String errorCode;
    private String errorMessage;

    public ResultModel(T returnValue) {
        this.isSuccess = true;
        this.returnValue = returnValue;
    }

    public ResultModel(String errorCode, String errorMessage) {
        this.isSuccess = false;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public T getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(T returnValue) {
        this.returnValue = returnValue;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 成功返回
     *
     * @param <T>
     * @return
     */
    public static <T> ResultModel<T> success(T returnValue) {
        return new ResultModel<T>(returnValue);
    }



    /**
     * 无效参数
     *
     * @param <T>
     * @return
     */
    public static <T> ResultModel<T> paramError() {

        return new ResultModel(ErrorConstants.INVALID_PARAM_CODE, ErrorConstants.INVALID_PARAM_MSG);
    }


    /**
     * 通用异常,用于业务错误
     *
     * @param <T>
     * @return
     */
    public static <T> ResultModel<T> commonError(String msg) {

        return new ResultModel(ErrorConstants.COMMON_ERROR_CODE, msg);
    }

    /**
     * 服务器异常
     *
     * @param <T>
     * @return
     */
    public static <T> ResultModel<T> serverError() {

        return new ResultModel(ErrorConstants.SERVER_ERROR_CODE, ErrorConstants.SERVER_ERROR_MSG);
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResultModel{");
        sb.append("isSuccess=").append(isSuccess);
        sb.append(", returnValue=").append(returnValue);
        sb.append(", errorCode='").append(errorCode).append('\'');
        sb.append(", errorMessage='").append(errorMessage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}



