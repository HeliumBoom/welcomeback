package com.ruoyi.welcomeback.controller;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.welcomeback.domain.FacePossessLog;
import com.ruoyi.welcomeback.service.IFacePossessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;

import static com.ruoyi.common.core.domain.AjaxResult.error;
import static com.ruoyi.common.utils.SecurityUtils.getLoginUser;

@RestController
@RequestMapping("/webcam_detect")
public class FacePossessController {
    private static final String cachePath = "D:\\ruoyi\\cache\\";

    @Autowired
    private IFacePossessService facePossessService;

    @Autowired
    private ISysUserService userService;

    @PostMapping("/uploads")
    @RepeatSubmit(interval = 4000)
    public AjaxResult uploadFiles(@RequestParam("face_detect_file") MultipartFile files) throws Exception {
        if (files != null && !files.isEmpty()) {
            //获取上传成功的图片保存路径，并返回给前台
            String picture = FileUploadUtils.upload(RuoYiConfig.getFrontWebcamPicPath(), files);
            if (!StringUtils.isEmpty(picture)) {
                // 创建临时文件
                File cacheFile = new File(cachePath + files.getName() + ".png");
                FileCopyUtils.copy(files.getBytes(), cacheFile);
                String absolutePath = cachePath + files.getName() + ".png";
//                System.out.println(absolutePath);
                String faces = facePossessService.recognizeFace(absolutePath);
                cacheFile.delete();

                String[] users = faces.split(",");
                StringBuilder retNames = new StringBuilder();
                for (int i = 0; i < users.length; i++) {
                    if (users[i] == null || users[i].equals("")) continue;
                    if (i != 0) retNames.append(',');
                    if ("unknow".equals(users[i]))
                        retNames.append("未登记人员");
                    else
                        retNames.append(userService.selectUserById(Long.valueOf(users[i])).getUserName());
                }

                // 获取是哪个摄像头
                LoginUser loginUser = getLoginUser();
                Long logId = facePossessService.insertEnterLog(loginUser.getUser().getSex().charAt(0), faces, loginUser.getUser().getUserName());
                FacePossessLog facePossessLog = new FacePossessLog();
                facePossessLog.setPath(picture);
                facePossessLog.setLogId(logId);
                facePossessLog.setCreateBy(loginUser.getUser().getUserName());
                facePossessService.insertFacePossessLog(facePossessLog);

                if ("0".equals(loginUser.getUser().getSex()))
                    retNames.append("进入小区");
                else
                    retNames.append("离开小区");

                AjaxResult ajax = AjaxResult.success();
                ajax.put("msg", retNames);
                ajax.put("imgUrl", picture);
                return ajax;
            }
        }
        return error("接口发生异常");
    }
}
