package com.ruoyi.welcomeback.mapper;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.welcomeback.domain.WebcamEnterLog;

/**
 * 往来人员出入日志Mapper接口
 *
 * @author Helium
 * @date 2023-05-05
 */
public interface WebcamEnterLogMapper
{
    /**
     * 查询往来人员出入日志
     *
     * @param logId 往来人员出入日志主键
     * @return 往来人员出入日志
     */
    public WebcamEnterLog selectWebcamEnterLogByLogId(Long logId);

    /**
     * 查询往来人员出入日志列表
     *
     * @param webcamEnterLog 往来人员出入日志
     * @return 往来人员出入日志集合
     */
    public List<WebcamEnterLog> selectWebcamEnterLogList(WebcamEnterLog webcamEnterLog);

    /**
     * 新增往来人员出入日志
     *
     * @param webcamEnterLog 往来人员出入日志
     * @return 结果
     */
    public int insertWebcamEnterLog(WebcamEnterLog webcamEnterLog);

    /**
     * 修改往来人员出入日志
     *
     * @param webcamEnterLog 往来人员出入日志
     * @return 结果
     */
    public int updateWebcamEnterLog(WebcamEnterLog webcamEnterLog);

    /**
     * 删除往来人员出入日志
     *
     * @param logId 往来人员出入日志主键
     * @return 结果
     */
    public int deleteWebcamEnterLogByLogId(Long logId);

    /**
     * 批量删除往来人员出入日志
     *
     * @param logIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWebcamEnterLogByLogIds(Long[] logIds);

    public Long insertLog(Character type, String residents, String creator, Date date);
}
