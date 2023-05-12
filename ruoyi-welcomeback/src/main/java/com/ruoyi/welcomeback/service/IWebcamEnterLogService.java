package com.ruoyi.welcomeback.service;

import java.util.List;
import com.ruoyi.welcomeback.domain.WebcamEnterLog;

/**
 * 往来人员出入日志Service接口
 *
 * @author Helium
 * @date 2023-05-05
 */
public interface IWebcamEnterLogService
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
     * 批量删除往来人员出入日志
     *
     * @param logIds 需要删除的往来人员出入日志主键集合
     * @return 结果
     */
    public int deleteWebcamEnterLogByLogIds(Long[] logIds);

    /**
     * 删除往来人员出入日志信息
     *
     * @param logId 往来人员出入日志主键
     * @return 结果
     */
    public int deleteWebcamEnterLogByLogId(Long logId);
}
