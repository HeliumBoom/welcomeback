package com.ruoyi.welcomeback.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.welcomeback.mapper.WebcamEnterLogMapper;
import com.ruoyi.welcomeback.domain.WebcamEnterLog;
import com.ruoyi.welcomeback.service.IWebcamEnterLogService;

/**
 * 往来人员出入日志Service业务层处理
 *
 * @author Helium
 * @date 2023-05-05
 */
@Service
public class WebcamEnterLogServiceImpl implements IWebcamEnterLogService
{
    @Autowired
    private WebcamEnterLogMapper webcamEnterLogMapper;

    /**
     * 查询往来人员出入日志
     *
     * @param logId 往来人员出入日志主键
     * @return 往来人员出入日志
     */
    @Override
    public WebcamEnterLog selectWebcamEnterLogByLogId(Long logId)
    {
        return webcamEnterLogMapper.selectWebcamEnterLogByLogId(logId);
    }

    /**
     * 查询往来人员出入日志列表
     *
     * @param webcamEnterLog 往来人员出入日志
     * @return 往来人员出入日志
     */
    @Override
    public List<WebcamEnterLog> selectWebcamEnterLogList(WebcamEnterLog webcamEnterLog)
    {
        return webcamEnterLogMapper.selectWebcamEnterLogList(webcamEnterLog);
    }

    /**
     * 新增往来人员出入日志
     *
     * @param webcamEnterLog 往来人员出入日志
     * @return 结果
     */
    @Override
    public int insertWebcamEnterLog(WebcamEnterLog webcamEnterLog)
    {
        webcamEnterLog.setCreateTime(DateUtils.getNowDate());
        return webcamEnterLogMapper.insertWebcamEnterLog(webcamEnterLog);
    }

    /**
     * 修改往来人员出入日志
     *
     * @param webcamEnterLog 往来人员出入日志
     * @return 结果
     */
    @Override
    public int updateWebcamEnterLog(WebcamEnterLog webcamEnterLog)
    {
        webcamEnterLog.setUpdateTime(DateUtils.getNowDate());
        return webcamEnterLogMapper.updateWebcamEnterLog(webcamEnterLog);
    }

    /**
     * 批量删除往来人员出入日志
     *
     * @param logIds 需要删除的往来人员出入日志主键
     * @return 结果
     */
    @Override
    public int deleteWebcamEnterLogByLogIds(Long[] logIds)
    {
        return webcamEnterLogMapper.deleteWebcamEnterLogByLogIds(logIds);
    }

    /**
     * 删除往来人员出入日志信息
     *
     * @param logId 往来人员出入日志主键
     * @return 结果
     */
    @Override
    public int deleteWebcamEnterLogByLogId(Long logId)
    {
        return webcamEnterLogMapper.deleteWebcamEnterLogByLogId(logId);
    }
}
