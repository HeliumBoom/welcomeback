package com.ruoyi.welcomeback.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import com.ruoyi.welcomeback.mapper.SysWebcamMapper;
import com.ruoyi.welcomeback.domain.SysWebcam;
import com.ruoyi.welcomeback.service.ISysWebcamService;

/**
 * 监控端Service业务层处理
 *
 * @author Helium
 * @date 2023-05-02
 */
@Service
public class SysWebcamServiceImpl implements ISysWebcamService
{
    @Autowired
    private SysWebcamMapper sysWebcamMapper;

    /**
     * 查询监控端
     *
     * @param monitorId 监控端主键
     * @return 监控端
     */
    @Override
    public SysWebcam selectSysWebcamByMonitorId(Long monitorId)
    {
        return sysWebcamMapper.selectSysWebcamByMonitorId(monitorId);
    }

    /**
     * 查询监控端列表
     *
     * @param sysWebcam 监控端
     * @return 监控端
     */
    @Override
    public List<SysWebcam> selectSysWebcamList(SysWebcam sysWebcam)
    {
        return sysWebcamMapper.selectSysWebcamList(sysWebcam);
    }

    /**
     * 新增监控端
     *
     * @param sysWebcam 监控端
     * @return 结果
     */
    @Override
    public int insertSysWebcam(SysWebcam sysWebcam)
    {
        sysWebcam.setCreateTime(DateUtils.getNowDate());
        return sysWebcamMapper.insertSysWebcam(sysWebcam);
    }

    /**
     * 修改监控端
     *
     * @param sysWebcam 监控端
     * @return 结果
     */
    @Override
    public int updateSysWebcam(SysWebcam sysWebcam)
    {
        sysWebcam.setUpdateTime(DateUtils.getNowDate());
        return sysWebcamMapper.updateSysWebcam(sysWebcam);
    }

    /**
     * 批量删除监控端
     *
     * @param monitorIds 需要删除的监控端主键
     * @return 结果
     */
    @Override
    public int deleteSysWebcamByMonitorIds(Long[] monitorIds)
    {
        return sysWebcamMapper.deleteSysWebcamByMonitorIds(monitorIds);
    }

    /**
     * 删除监控端信息
     *
     * @param monitorId 监控端主键
     * @return 结果
     */
    @Override
    public int deleteSysWebcamByMonitorId(Long monitorId)
    {
        return sysWebcamMapper.deleteSysWebcamByMonitorId(monitorId);
    }
}
