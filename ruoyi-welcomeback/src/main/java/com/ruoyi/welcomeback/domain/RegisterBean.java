package com.ruoyi.welcomeback.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 信息登记记录对象 sys_register
 *
 * @author Helium
 * @date 2023-05-09
 */
public class RegisterBean extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 信息登记id */
    private Long registerId;

    /** 相关用户id */
    @Excel(name = "相关用户id")
    private Long userId;

    /** 用户真实姓名 */
    @Excel(name = "用户真实姓名")
    private String realName;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phoneNumber;

    /** 所属楼栋id */
    @Excel(name = "所属楼栋id")
    private Long deptId;

    /** 来访日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "来访日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginDate;

    /** 离开日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "离开日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 人脸图片存储路径 */
    @Excel(name = "人脸图片存储路径")
    private String path;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private String status;

    public void setRegisterId(Long registerId)
    {
        this.registerId = registerId;
    }

    public Long getRegisterId()
    {
        return registerId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getRealName()
    {
        return realName;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }

    public Date getBeginDate()
    {
        return beginDate;
    }
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }
    public void setPath(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return path;
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
                .append("registerId", getRegisterId())
                .append("userId", getUserId())
                .append("realName", getRealName())
                .append("phoneNumber", getPhoneNumber())
                .append("deptId", getDeptId())
                .append("beginDate", getBeginDate())
                .append("endDate", getEndDate())
                .append("path", getPath())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
