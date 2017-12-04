/**
 * Copyright (c) 2016, 791650277@qq.com(Mr.kiwi) All Rights Reserved.
 */
package com.fshows.proxy.myutil;

import com.fshows.proxy.util.DateUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class QiniuUtil {


    /**
     * 七牛配置
     */
    public  static String ACCESS_KEY = "e-NYZOExQciWS8jHAyiBN90Y-7gkXXlxuxqsXSYt";
    public static String SECRET_KEY = "XmDsWxexf5McTjiXuXQGpeANzA4TrmgEF_xja3oQ";
    public static String ACCESS_URL = "http://file-kf.wechatpark.com/";

    public static String OPEN_ACCESS_KEY = "e-NYZOExQciWS8jHAyiBN90Y-7gkXXlxuxqsXSYt";
    public static String OPEN_SECRET_KEY = "XmDsWxexf5McTjiXuXQGpeANzA4TrmgEF_xja3oQ";
    public static String OPEN_ACCESS_URL = "https://img-kf.51youdian.com/";


    private static final Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

    // 覆盖上传 私有库
    private static String getUpToken(String bucketName, String key) {
        //密钥配置
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        //<bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
        return auth.uploadToken(bucketName, key);
    }

    // 覆盖上传 公有库
    private static String getOpenUpToken(String bucketName, String key) {
        //密钥配置
        Auth auth = Auth.create(OPEN_ACCESS_KEY, OPEN_SECRET_KEY);
        //<bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
        return auth.uploadToken(bucketName, key);
    }

    /**
     * 生成一个随机key前缀(目前20位)
     * 20160809104636 234610196920
     * 14位日期 6位随机数字
     *
     * @return
     */
    public static String createKeyPrefix() {
        StringBuffer sb = new StringBuffer();
        // 获得时间字符串
        sb.append(DateUtil.getNowDateTimeStr());
        sb.append("mymone");
        // 获得6位随机数字
        sb.append(RandomStringUtils.randomNumeric(6));

        return sb.toString();
    }

    /**
     * 上传指定文件
     *
     * @param bucketName 空间名称
     * @param key        上传后文件名
     * @param filePath   要上传的文件名称
     * @param type       上传类型 1:私有 2:公有
     * @throws IOException
     */
    public static Boolean upload(String bucketName, String key, String filePath, Integer type) {
        try {
            Configuration cfg = new Configuration(Zone.autoZone());
            //创建上传对象
            UploadManager uploadManager = new UploadManager(cfg);
            //上传token
            String upToken;
            switch (type) {
                case 1:
                    upToken = getUpToken(bucketName, key);
                    break;
                case 2:
                    upToken = getOpenUpToken(bucketName, key);
                    break;
                default:
                    return Boolean.FALSE;
            }
            //调用put方法上传，这里指定的key和上传策略中的key要一致
            Response res = uploadManager.put(filePath, key, upToken);

            return Boolean.TRUE;

        } catch (QiniuException e) {
            Response r = e.response;
            try {
                //响应的文本信息
                logger.error("七牛上传文件时出现异常：e = {}", r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }

            return Boolean.FALSE;
        }
    }

    /**
     * 上传指定文件
     *
     * @param bucketName 空间名称
     * @param key        上传后文件名
     * @param file       要上传的文件
     * @param type       上传类型 1:私有 2:公有
     * @throws IOException
     */
    public static Boolean uploadByFile(String bucketName, String key, File file, Integer type) throws IOException {
        try {
            Configuration cfg = new Configuration(Zone.autoZone());
            //创建上传对象
            UploadManager uploadManager = new UploadManager(cfg);
            //上传token
            String upToken;
            switch (type) {
                case 1:
                    upToken = getUpToken(bucketName, key);
                    break;
                    //公共库
                case 2:
                    upToken = getOpenUpToken(bucketName, key);
                    break;
                default:
                    return Boolean.FALSE;
            }
            //调用put方法上传，这里指定的key和上传策略中的key要一致
            Response res = uploadManager.put(file, key, upToken);

            return Boolean.TRUE;
        } catch (QiniuException e) {
            Response r = e.response;
            try {
                //响应的文本信息
                logger.error("七牛上传文件时出现异常：e = {}", r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }

            return Boolean.FALSE;
        }
    }

    /**
     * 上传指定文件
     *
     * @param bucketName 空间名称
     * @param key        上传后文件名
     * @param is         要上传的文件流
     * @param type       上传类型 1:私有 2:公有
     * @throws IOException
     */
    public static Boolean uploadByIs(String bucketName, String key, InputStream is, Integer type) throws IOException {
        try {
            Configuration cfg = new Configuration(Zone.autoZone());
            //创建上传对象
            UploadManager uploadManager = new UploadManager(cfg);
            //上传token
            String upToken;
            switch (type) {
                case 1:
                    upToken = getUpToken(bucketName, key);
                    break;
                case 2:
                    upToken = getOpenUpToken(bucketName, key);
                    break;
                default:
                    return Boolean.FALSE;
            }
            //调用put方法上传，这里指定的key和上传策略中的key要一致
            Response res = uploadManager.put(is, key, upToken, null, null);

            return Boolean.TRUE;
        } catch (QiniuException e) {
            Response r = e.response;
            try {
                //响应的文本信息
                logger.error("七牛上传文件时出现异常：e = {}", r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }

            return Boolean.FALSE;
        }
    }

    /**
     * 获得下载地址
     *
     * @param key
     * @return
     */
    public static String download(String key, Long time) {

        //密钥配置
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        //构造私有空间需要生成的下载的链接
        String URL = ACCESS_URL + key;

        //调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
        return auth.privateDownloadUrl(URL, time);

    }

    public static void main(String[] args) {
        System.err.println(download("kfshop/dayFile/kfshopDayOrder20171130.csv", 3600L));
    }
}
