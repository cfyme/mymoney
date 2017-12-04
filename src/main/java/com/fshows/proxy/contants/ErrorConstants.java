/**
 * Copyright (c) 2016, 791650277@qq.com(Mr.kiwi) All Rights Reserved.
 */
package com.fshows.proxy.contants;

/**
 * 项目：liquidator-platform-admin
 * 包名：com.fshows.liquidator.platform.admin.common.constants
 * 功能：返回错误的常量
 * 时间：2016-08-02 16:16
 * 作者：Mr.Kiwi
 */
public interface ErrorConstants {

    /**
     * 无效sign
     */
    String INVALID_SIGN_CODE = "-100";
    String INVALID_SIGN_MSG = "\u65e0\u6548\u7b7e\u540d";

    /**
     * 无效参数
     */
    String INVALID_PARAM_CODE = "-101";
    String INVALID_PARAM_MSG = "\u65e0\u6548\u53c2\u6570";

    /**
     * 权限不足
     */
    String PERMISSION_DENIED_CODE = "-102";
    String PERMISSION_DENIED_MSG = "\u6743\u9650\u4e0d\u8db3";

    /**
     * 通用错误
     */
    String COMMON_ERROR_CODE = "-103";
    String COMMON_ERROR_MSG = "\u901a\u7528\u9519\u8bef";

    /**
     * 登录失效
     */
    String INVALID_LOGIN_CODE = "-104";
    String INVALID_LOGIN_MSG = "\u767b\u5f55\u5931\u6548";

    /**
     * 数据库操作失败
     */
    String DATA_BASE_CODE = "-105";
    String DATA_BASE_MSG = "\u6570\u636e\u5e93\u64cd\u4f5c\u5f02\u5e38";

    /**
     * token失效
     */
    String INVALID_TOKEN_CODE = "-106";
    String INVALID_TOKEN_MSG = "invalid token";

    /**
     * 服务器异常
     */
    String SERVER_ERROR_CODE = "-200";
    String SERVER_ERROR_MSG = "\u670d\u52a1\u5668\u5f02\u5e38";

    /**
     * 支付中状态
     */
    String USER_PAYING_CODE = "100";
    String USER_PAYING_MSG = "用户正在支付中。。。";

    /**
     * 更新余额失败
     */
    String UPDATE_BALANCE_FAILED_CODE = "10001";
    String UPDATE_BALANCE_FAILED_MSG = "\u66f4\u65b0\u4f59\u989d\u5931\u8d25";

    String INSERT_BALANCE_HIS_FAILED_CODE = "10002";
    String INSERT_BALANCE_HIS_FAILED_MSG = "\u63d2\u5165\u4f59\u989d\u8bb0\u5f55\u8868\u5931\u8d25";

    String NO_BANK_BAND_CODE = "10003";
    String NO_BANK_BAND_MSG = "\u94f6\u884c\u5361\u5b58\u5728\u98ce\u9669 \u8bf7\u91cd\u65b0\u7ed1\u5361";
}
