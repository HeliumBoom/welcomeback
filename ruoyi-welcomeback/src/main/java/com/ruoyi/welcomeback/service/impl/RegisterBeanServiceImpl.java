package com.ruoyi.welcomeback.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.welcomeback.mapper.RegisterBeanMapper;
import com.ruoyi.welcomeback.domain.RegisterBean;
import com.ruoyi.welcomeback.service.IRegisterBeanService;

/**
 * 信息登记记录Service业务层处理
 *
 * @author Helium
 * @date 2023-05-09
 */
@Service
public class RegisterBeanServiceImpl implements IRegisterBeanService
{
    @Autowired
    private RegisterBeanMapper registerBeanMapper;

    /**
     * 查询信息登记记录
     *
     * @param registerId 信息登记记录主键
     * @return 信息登记记录
     */
    @Override
    public RegisterBean selectRegisterBeanByRegisterId(Long registerId)
    {
        return registerBeanMapper.selectRegisterBeanByRegisterId(registerId);
    }

    /**
     * 查询信息登记记录列表
     *
     * @param registerBean 信息登记记录
     * @return 信息登记记录
     */
    @Override
    public List<RegisterBean> selectRegisterBeanList(RegisterBean registerBean)
    {
        return registerBeanMapper.selectRegisterBeanList(registerBean);
    }

    /**
     * 新增信息登记记录
     *
     * @param registerBean 信息登记记录
     * @return 结果
     */
    @Override
    public int insertRegisterBean(RegisterBean registerBean)
    {
        registerBean.setCreateTime(DateUtils.getNowDate());
        return registerBeanMapper.insertRegisterBean(registerBean);
    }

    /**
     * 修改信息登记记录
     *
     * @param registerBean 信息登记记录
     * @return 结果
     */
    @Override
    public int updateRegisterBean(RegisterBean registerBean)
    {
        registerBean.setUpdateTime(DateUtils.getNowDate());
        return registerBeanMapper.updateRegisterBean(registerBean);
    }

    /**
     * 批量删除信息登记记录
     *
     * @param registerIds 需要删除的信息登记记录主键
     * @return 结果
     */
    @Override
    public int deleteRegisterBeanByRegisterIds(Long[] registerIds)
    {
        return registerBeanMapper.deleteRegisterBeanByRegisterIds(registerIds);
    }

    /**
     * 删除信息登记记录信息
     *
     * @param registerId 信息登记记录主键
     * @return 结果
     */
    @Override
    public int deleteRegisterBeanByRegisterId(Long registerId)
    {
        return registerBeanMapper.deleteRegisterBeanByRegisterId(registerId);
    }

    @Override
    public int registerResident(Long registerId) {
        return registerBeanMapper.registerResident(registerId);
    }
}
