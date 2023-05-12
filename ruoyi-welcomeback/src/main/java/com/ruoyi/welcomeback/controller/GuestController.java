package com.ruoyi.welcomeback.controller;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.welcomeback.domain.RegisterBean;
import com.ruoyi.welcomeback.service.IRegisterBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.ruoyi.common.utils.SecurityUtils.getLoginUser;

@RestController
@RequestMapping("/register")
public class GuestController {
    private static final String FDBPath = "D:\\ruoyi\\FaceDatabase\\";

    private static final String ProfilePath = "D:/ruoyi/uploadPath/";

    @Autowired
    IRegisterBeanService registerBeanService;

    @Autowired
    ISysUserService userService;

    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    public void insertIntoFaceDatabase(MultipartFile file, Long uid) throws Exception {
        if (file.isEmpty()) {
            return;
        }
        File cacheFile = new File(FDBPath + uid + ".png");
        FileCopyUtils.copy(file.getBytes(), cacheFile);
    }

    public void sendCelebratoryEmail(String userName, String emailAddress) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject("您的居民信息审核已经通过啦！");
        simpleMailMessage.setText("恭喜您，您在乐归居民系统申报的信息已经由管理员审核通过了！");
        mailSender.send(simpleMailMessage);
    }

    public void updateFacePic(Long userId, String path) throws IOException {
        File tarFile = new File(FDBPath + userId + ".png");
        File srcFile = new File(path.replaceAll("/profile", ProfilePath));
        FileCopyUtils.copy(srcFile, tarFile);
    }

    @PostMapping("/guest")
    public AjaxResult registerGuest(@RequestParam("realName") String realName, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("visitDate") String visitDate, @RequestParam("facePic") MultipartFile facePic) throws Exception {
        // 这边拿到了登记的信息，然后就应该做登记
        String picture = FileUploadUtils.upload(RuoYiConfig.getRegisterPicPath(), facePic);
        String[] dates = visitDate.split(",");
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date beginDate = ft.parse(dates[0] + " 00:00:00");
        Date endDate = ft.parse(dates[1] + " 23:59:59");

        LoginUser loginUser = getLoginUser();

        RegisterBean bean = new RegisterBean();
        bean.setUserId(loginUser.getUser().getUserId());
        bean.setRealName(realName);
        bean.setPath(picture);
        bean.setPhoneNumber(phoneNumber);
        bean.setBeginDate(beginDate);
        bean.setEndDate(endDate);

        registerBeanService.insertRegisterBean(bean);

        insertIntoFaceDatabase(facePic, bean.getUserId());
        return AjaxResult.success("登记成功");
    }

    @PostMapping("/resident")
    public AjaxResult registerResident(@RequestParam("realName") String realName, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("dept") String dept, @RequestParam("facePic") MultipartFile facePic) throws Exception {
        String picture = FileUploadUtils.upload(RuoYiConfig.getRegisterPicPath(), facePic);
        LoginUser loginUser = getLoginUser();

        RegisterBean bean = new RegisterBean();
        bean.setRealName(realName);
        bean.setUserId(loginUser.getUser().getUserId());
        bean.setPath(picture);
        bean.setPhoneNumber(phoneNumber);
        bean.setDeptId(Long.valueOf(dept));

        registerBeanService.insertRegisterBean(bean);
        insertIntoFaceDatabase(facePic, bean.getUserId());
        return AjaxResult.success("登记成功");
    }

    @GetMapping("/check")
    public AjaxResult checkResident(@RequestParam String registerId) throws Exception {
        registerBeanService.registerResident(Long.valueOf(registerId));
        RegisterBean registerBean = registerBeanService.selectRegisterBeanByRegisterId(Long.valueOf(registerId));
        SysUser sysUser = userService.selectUserById(registerBean.getUserId());
        sendCelebratoryEmail(sysUser.getUserName(), sysUser.getEmail());
        updateFacePic(sysUser.getUserId(), registerBean.getPath());
        return AjaxResult.success();
    }
}
