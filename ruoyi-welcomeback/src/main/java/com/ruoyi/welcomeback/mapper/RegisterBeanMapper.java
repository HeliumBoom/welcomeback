package com.ruoyi.welcomeback.mapper;

import java.util.List;
import com.ruoyi.welcomeback.domain.RegisterBean;

/**
 * 信息登记记录Mapper接口
 *
 * @author Helium
 * @date 2023-05-09
 */
public interface RegisterBeanMapper
{
    /**
     * 查询信息登记记录
     *
     * @param registerId 信息登记记录主键
     * @return 信息登记记录
     */
    public RegisterBean selectRegisterBeanByRegisterId(Long registerId);

    /**
     * 查询信息登记记录列表
     *
     * @param registerBean 信息登记记录
     * @return 信息登记记录集合
     */
    public List<RegisterBean> selectRegisterBeanList(RegisterBean registerBean);

    /**
     * 新增信息登记记录
     *
     * @param registerBean 信息登记记录
     * @return 结果
     */
    public int insertRegisterBean(RegisterBean registerBean);

    /**
     * 修改信息登记记录
     *
     * @param registerBean 信息登记记录
     * @return 结果
     */
    public int updateRegisterBean(RegisterBean registerBean);

    /**
     * 删除信息登记记录
     *
     * @param registerId 信息登记记录主键
     * @return 结果
     */
    public int deleteRegisterBeanByRegisterId(Long registerId);

    /**
     * 批量删除信息登记记录
     *
     * @param registerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRegisterBeanByRegisterIds(Long[] registerIds);

    public int registerResident(Long registerId);
}
