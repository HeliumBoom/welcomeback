package com.ruoyi.welcomeback.service;

import java.util.List;
import com.ruoyi.welcomeback.domain.FacePossessLog;

/**
 * 监控捕获的人脸图片Service接口
 *
 * @author Helium
 * @date 2023-05-05
 */
public interface IFacePossessService
{
    /**
     * 给定人脸图片路径，然后调用python识别人脸并将结果返回
     */
    public String recognizeFace(String path);
    /**
     * 查询监控捕获的人脸图片
     *
     * @param faceId 监控捕获的人脸图片主键
     * @return 监控捕获的人脸图片
     */
    public FacePossessLog selectFacePossessLogByFaceId(Long faceId);

    /**
     * 查询监控捕获的人脸图片列表
     *
     * @param facePossessLog 监控捕获的人脸图片
     * @return 监控捕获的人脸图片集合
     */
    public List<FacePossessLog> selectFacePossessLogList(FacePossessLog facePossessLog);

    /**
     * 新增监控捕获的人脸图片
     *
     * @param facePossessLog 监控捕获的人脸图片
     * @return 结果
     */
    public int insertFacePossessLog(FacePossessLog facePossessLog);

    /**
     * 修改监控捕获的人脸图片
     *
     * @param facePossessLog 监控捕获的人脸图片
     * @return 结果
     */
    public int updateFacePossessLog(FacePossessLog facePossessLog);

    /**
     * 批量删除监控捕获的人脸图片
     *
     * @param faceIds 需要删除的监控捕获的人脸图片主键集合
     * @return 结果
     */
    public int deleteFacePossessLogByFaceIds(Long[] faceIds);

    /**
     * 删除监控捕获的人脸图片信息
     *
     * @param faceId 监控捕获的人脸图片主键
     * @return 结果
     */
    public int deleteFacePossessLogByFaceId(Long faceId);

    public Long insertEnterLog(Character type, String residents, String creator);
}
