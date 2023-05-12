package com.ruoyi.welcomeback.mapper;

import java.util.List;
import com.ruoyi.welcomeback.domain.SysWebcam;

/**
 * 监控端Mapper接口
 *
 * @author Helium
 * @date 2023-05-02
 */
public interface SysWebcamMapper
{
    /**
     * 查询监控端
     *
     * @param monitorId 监控端主键
     * @return 监控端
     */
    public SysWebcam selectSysWebcamByMonitorId(Long monitorId);

    /**
     * 查询监控端列表
     *
     * @param sysWebcam 监控端
     * @return 监控端集合
     */
    public List<SysWebcam> selectSysWebcamList(SysWebcam sysWebcam);

    public List<SysWebcam> selectSysWebcamListVo();

    /**
     * 新增监控端
     *
     * @param sysWebcam 监控端
     * @return 结果
     */
    public int insertSysWebcam(SysWebcam sysWebcam);

    /**
     * 修改监控端
     *
     * @param sysWebcam 监控端
     * @return 结果
     */
    public int updateSysWebcam(SysWebcam sysWebcam);

    /**
     * 删除监控端
     *
     * @param monitorId 监控端主键
     * @return 结果
     */
    public int deleteSysWebcamByMonitorId(Long monitorId);

    /**
     * 批量删除监控端
     *
     * @param monitorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysWebcamByMonitorIds(Long[] monitorIds);
}
