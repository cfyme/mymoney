package com.fshows.proxy.service;

import com.fshows.proxy.myutil.QiniuUtil;
import com.fshows.proxy.result.ResultModel;
import com.google.common.collect.Maps;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目：fs-fubei-shop
 * 包名：com.fshows.fubei.shop.service
 * 功能：
 * 时间：2017-02-16
 * 作者：呱牛
 */
@Service
public class ApiCommonService {

    /**
     * 图片文件上传的临时目录
     */
   public  static  String IMG_FILE_SAVE_TEMP_DIR = "/tmp" +
            File.separator + "files" +
            File.separator + "upload" +
            File.separator;

    /**
     * 图片文件存入七牛的前缀名
     */
    static  String IMG_FILE_PRE = "kfshop/img/mymoney/";



    public  static  String FILE_BUCKETNAME  = "kfshop";
    public  static  String IMG_BUCKETNAME = "kfshop-img";

    private static final Logger logger = LoggerFactory.getLogger(ApiCommonService.class);

    /**
     * 上传图片
     *
     * @param multipartFile
     * @return
     */
    public ResultModel uploadAuthImg(MultipartFile multipartFile) {
        try {
            String[] fileNameParts = StringUtils.split(multipartFile.getOriginalFilename(), ".");
            String extName = "png";
            if (fileNameParts.length > 1) {
                extName = fileNameParts[1];
            }

            String fileName = QiniuUtil.createKeyPrefix() + "." + extName;
            String filePath = IMG_FILE_SAVE_TEMP_DIR + fileName;
            String key = IMG_FILE_PRE + fileName;

            logger.info("fileName={}", fileName);
            logger.info("filePath={}", filePath);
            logger.info("key={}", key);

            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            file.createNewFile();
            //复制文件
            multipartFile.transferTo(file);


            QiniuUtil.uploadByFile(IMG_BUCKETNAME, key, file, 2);

            file.delete();

            Map<String, String> map = Maps.newHashMap();
            String url  = QiniuUtil.OPEN_ACCESS_URL + key;
            map.put("url", url);

            logger.info("uploadAuthImg end >> url: {}", url);

            return ResultModel.success(map);

        } catch (IOException e) {
            logger.error("上传认证图片失败, ex = {}", ExceptionUtils.getStackTrace(e));
            return ResultModel.serverError();
        }
    }




    public ResultModel uploadBase64(String base64Data) {
        try {

            String dataPrix = "";
            String data = "";

            logger.debug("对数据进行判断");
            if(base64Data == null || "".equals(base64Data)){
                return ResultModel.commonError("上传失败，上传图片数据为空");
            }else{
                String [] d = base64Data.split("base64,");
                if(d != null && d.length == 2){
                    dataPrix = d[0];
                    data = d[1];
                }else{
                    return ResultModel.commonError("上传失败，数据不合法");

                }
            }


            //data:image/svg+xml

//            String suffix = "";
//            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
//                suffix = ".jpg";
//            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
//                suffix = ".ico";
//            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
//                suffix = ".gif";
//            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
//                suffix = ".png";
//            }else{
//                return ResultModel.commonError("上传图片格式不合法");
//            }
//


            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(data);


            String fileName = QiniuUtil.createKeyPrefix() + "." + "png";


            String filePath = IMG_FILE_SAVE_TEMP_DIR + fileName;
            String key = IMG_FILE_PRE + fileName;


            InputStream is = new ByteArrayInputStream(bs);

            QiniuUtil.uploadByIs(IMG_BUCKETNAME, key, is, 2);


            Map<String, String> map = Maps.newHashMap();
            String url  = QiniuUtil.OPEN_ACCESS_URL + key;
            map.put("url", url);

            logger.info("uploadAuthImg end >> url: {}", url);

            return ResultModel.success(map);

        } catch (IOException e) {
            logger.error("上传认证图片失败, ex = {}", ExceptionUtils.getStackTrace(e));
            return ResultModel.serverError();
        }
    }


}
