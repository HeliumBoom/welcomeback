package com.ruoyi.welcomeback.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 监控捕获的人脸图片对象 FacePossess
 *
 * @author Helium
 * @date 2023-05-05
 */
public class FacePossessLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    @Excel(name = "记录ID")
    private Long faceId;

    private Long logId;

    /** 人脸图片存储路径 */
    @Excel(name = "人脸图片存储路径")
    private String path;

    /** 删除标记 */
    private String delFlag;

    /** 启用状态 */
    @Excel(name = "启用状态")
    private String status;

    public void setFaceId(Long faceId)
    {
        this.faceId = faceId;
    }

    public Long getFaceId()
    {
        return faceId;
    }
    public void setPath(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return path;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    @Override
    public String toString() {
        return "FacePossessLog{" +
                "faceId=" + faceId +
                ", logId=" + logId +
                ", path='" + path + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
