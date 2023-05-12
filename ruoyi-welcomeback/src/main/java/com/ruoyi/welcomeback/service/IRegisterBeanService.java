package com.ruoyi.welcomeback.service;

import java.util.List;
import com.ruoyi.welcomeback.domain.RegisterBean;

/**
 * 信息登记记录Service接口
 *
 * @author Helium
 * @date 2023-05-09
 */
public interface IRegisterBeanService
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
     * 批量删除信息登记记录
     *
     * @param registerIds 需要删除的信息登记记录主键集合
     * @return 结果
     */
    public int deleteRegisterBeanByRegisterIds(Long[] registerIds);

    /**
     * 删除信息登记记录信息
     *
     * @param registerId 信息登记记录主键
     * @return 结果
     */
    public int deleteRegisterBeanByRegisterId(Long registerId);

    public int registerResident(Long registerId);
}
