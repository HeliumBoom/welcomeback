package com.ruoyi.welcomeback.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.welcomeback.domain.FacePossessLog;
import com.ruoyi.welcomeback.mapper.FacePossessMapper;
import com.ruoyi.welcomeback.mapper.WebcamEnterLogMapper;
import com.ruoyi.welcomeback.service.IFacePossessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 监控捕获的人脸图片Service业务层处理
 *
 * @author Helium
 * @date 2023-05-05
 */
@Service
public class FacePossessServiceImpl implements IFacePossessService
{
    private static final String scriptPath = "E:\\face.evoLVe-master\\paddle\\PaddleInference-demo\\client.py";

    @Autowired
    private FacePossessMapper facePossessMapper;

    @Autowired
    private WebcamEnterLogMapper webcamEnterLogMapper;

    public String recognizeFace(String path) {
        try {
            Process process = Runtime.getRuntime().exec("python " + scriptPath + " " + path);
            // 读取脚本输出
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }
            // 关闭输入流
            in.close();
            return stringBuilder.toString();
        } catch (Exception ignored){}
        return "";
    }

    /**
     * 查询监控捕获的人脸图片
     *
     * @param faceId 监控捕获的人脸图片主键
     * @return 监控捕获的人脸图片
     */
    @Override
    public FacePossessLog selectFacePossessLogByFaceId(Long faceId)
    {
        return facePossessMapper.selectFacePossessLogByFaceId(faceId);
    }

    /**
     * 查询监控捕获的人脸图片列表
     *
     * @param facePossessLog 监控捕获的人脸图片
     * @return 监控捕获的人脸图片
     */
    @Override
    public List<FacePossessLog> selectFacePossessLogList(FacePossessLog facePossessLog)
    {
        return facePossessMapper.selectFacePossessLogList(facePossessLog);
    }

    /**
     * 新增监控捕获的人脸图片
     *
     * @param facePossessLog 监控捕获的人脸图片
     * @return 结果
     */
    @Override
    public int insertFacePossessLog(FacePossessLog facePossessLog)
    {
        facePossessLog.setCreateTime(DateUtils.getNowDate());
        return facePossessMapper.insertFacePossessLog(facePossessLog);
    }

    /**
     * 修改监控捕获的人脸图片
     *
     * @param facePossessLog 监控捕获的人脸图片
     * @return 结果
     */
    @Override
    public int updateFacePossessLog(FacePossessLog facePossessLog)
    {
        facePossessLog.setUpdateTime(DateUtils.getNowDate());
        return facePossessMapper.updateFacePossessLog(facePossessLog);
    }

    /**
     * 批量删除监控捕获的人脸图片
     *
     * @param faceIds 需要删除的监控捕获的人脸图片主键
     * @return 结果
     */
    @Override
    public int deleteFacePossessLogByFaceIds(Long[] faceIds)
    {
        return facePossessMapper.deleteFacePossessLogByFaceIds(faceIds);
    }

    /**
     * 删除监控捕获的人脸图片信息
     *
     * @param faceId 监控捕获的人脸图片主键
     * @return 结果
     */
    @Override
    public int deleteFacePossessLogByFaceId(Long faceId)
    {
        return facePossessMapper.deleteFacePossessLogByFaceId(faceId);
    }

    @Override
    public Long insertEnterLog(Character type, String residents, String creator) {
        return webcamEnterLogMapper.insertLog(type, residents, creator, DateUtils.getNowDate());
    }
}
