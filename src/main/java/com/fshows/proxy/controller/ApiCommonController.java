package com.fshows.proxy.controller;

import com.fshows.proxy.result.ResultModel;
import com.fshows.proxy.service.ApiCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/common")
public class ApiCommonController {

    private static final Logger logger = LoggerFactory.getLogger(QueryController.class);


    @Resource
    private ApiCommonService commonService;


    /**
     * 上传图片
     *
     * @return
     */
    @RequestMapping(value = "/uploadAuthImgOld", method = RequestMethod.POST)
    public ResultModel uploadAuthImgOld(@RequestParam(value = "file") MultipartFile file) {

        logger.info("----------uploadAuthImg-----------");
        logger.info("1, uploadAuthImg >> file="+file);

        if (file == null) {
            return ResultModel.paramError();
        }

        logger.info("2, uploadAuthImg >> start to upload "+file);
        return commonService.uploadAuthImg(file);

    }




    /**
     * 上传图片
     *
     * @return
     */
    @RequestMapping(value = "/uploadBase64", method = RequestMethod.POST)
    public ResultModel uploadBase64(@RequestParam String base64Data) {

        logger.info("----------uploadAuthImg-----------");
        logger.info("1, uploadBase64 >> base64Data="+base64Data);

        if (base64Data == null) {
            return ResultModel.paramError();
        }

        logger.info("2, uploadBase64 >> start to upload");

        return commonService.uploadBase64(base64Data);

    }


}
