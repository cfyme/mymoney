package com.fshows.proxy.controller;

import com.fshows.proxy.contants.MyConstants;
import com.fshows.proxy.myutil.MyFileUtil;
import com.fshows.proxy.result.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.acl.LastOwnerException;
@RestController
public class QueryController {

    private static final Logger logger = LoggerFactory.getLogger(QueryController.class);

    @RequestMapping("/test")
    public void test1(HttpServletRequest req, HttpServletResponse response) throws Exception {
        logger.info("------------terst---------------");
    }


    @RequestMapping("/download")
    public void download(HttpServletRequest req, HttpServletResponse res) throws Exception {
        logger.info("--------download start--------------");

        StringBuffer content = new StringBuffer("");
        String fileName = MyConstants.fileName;

        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");

    }

    @RequestMapping("/query")
    public void test1r(HttpServletRequest req, HttpServletResponse response) throws Exception {
        logger.info("--------query start--------------");
        StringBuffer content = new StringBuffer("");
        String fileName = MyConstants.fileName;

        try {

            FileInputStream fis = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            String[] arrs = null;
            while ((line = br.readLine()) != null) {
                logger.info("line=" + line);
                content.append(line).append("</br>");
            }

            logger.info("--------query end--------------");


            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().print(content);

            br.close();
            isr.close();
            fis.close();

        } catch (Exception e) {
            logger.info("query error", e);

        }

    }


    @RequestMapping("/save")
    public String save(HttpServletRequest req, HttpServletResponse response) throws Exception {
        logger.info("------------save---------------");

        String name = req.getParameter("name");
        String idcard = req.getParameter("idcard");
        String bankNo = req.getParameter("bankNo");
        String bankName = req.getParameter("bankName");
        String address = req.getParameter("address");
        String mobile = req.getParameter("mobile");

        logger.info("name=" + name);
        logger.info("idcard=" + idcard);
        logger.info("bankNo=" + bankNo);
        logger.info("bankName=" + bankName);
        logger.info("address=" + address);

        StringBuffer buffer = new StringBuffer("");

        if (name == null) name = "";
        if (idcard == null) idcard = "";
        if (bankNo == null) bankNo = "";
        if (bankName == null) bankName = "";
        if (address == null) address = "";


        buffer.append(name).append(",");
        buffer.append(idcard).append(",");
        buffer.append(bankNo).append(",");
        buffer.append(bankName).append(",");
        buffer.append(mobile).append(",");
        buffer.append(address);

        String filename = MyConstants.fileName;

        try {

            MyFileUtil.method1(filename, buffer.toString());
            logger.info("------save success---------------");

            return "success";

        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return "fail";

        }

    }

}
