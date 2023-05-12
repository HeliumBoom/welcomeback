package com.ruoyi.welcomeback.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 监控端对象 sys_webcam
 *
 * @author Helium
 * @date 2023-05-02
 */
public class SysWebcam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long monitorId;

    /** 名字 */
    @Excel(name = "名字")
    private String monitorName;

    /** URL */
    @Excel(name = "URL")
    private String monitorUrl;

    /** 访问类型 */
    @Excel(name = "访问类型")
    private String permissionLevel;

    /** 删除标志 */
    private String delFlag;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setMonitorId(Long monitorId)
    {
        this.monitorId = monitorId;
    }

    public Long getMonitorId()
    {
        return monitorId;
    }
    public void setMonitorName(String monitorName)
    {
        this.monitorName = monitorName;
    }

    public String getMonitorName()
    {
        return monitorName;
    }
    public void setMonitorUrl(String monitorUrl)
    {
        this.monitorUrl = monitorUrl;
    }

    public String getMonitorUrl()
    {
        return monitorUrl;
    }
    public void setPermissionLevel(String permissionLevel)
    {
        this.permissionLevel = permissionLevel;
    }

    public String getPermissionLevel()
    {
        return permissionLevel;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("monitorId", getMonitorId())
                .append("monitorName", getMonitorName())
                .append("monitorUrl", getMonitorUrl())
                .append("permissionLevel", getPermissionLevel())
                .append("delFlag", getDelFlag())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
