package com.ruoyi.welcomeback.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.welcomeback.domain.RegisterBean;
import com.ruoyi.welcomeback.service.IRegisterBeanService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 信息登记记录Controller
 *
 * @author Helium
 * @date 2023-05-09
 */
@RestController
@RequestMapping("/welcomeback/register")
public class RegisterBeanController extends BaseController
{
    @Autowired
    private IRegisterBeanService registerBeanService;

    /**
     * 查询信息登记记录列表
     */
    @PreAuthorize("@ss.hasPermi('welcomeback:register:list')")
    @GetMapping("/list")
    public TableDataInfo list(RegisterBean registerBean)
    {
        startPage();
        List<RegisterBean> list = registerBeanService.selectRegisterBeanList(registerBean);
        return getDataTable(list);
    }

    /**
     * 导出信息登记记录列表
     */
    @PreAuthorize("@ss.hasPermi('welcomeback:register:export')")
    @Log(title = "信息登记记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RegisterBean registerBean)
    {
        List<RegisterBean> list = registerBeanService.selectRegisterBeanList(registerBean);
        ExcelUtil<RegisterBean> util = new ExcelUtil<RegisterBean>(RegisterBean.class);
        util.exportExcel(response, list, "信息登记记录数据");
    }

    /**
     * 获取信息登记记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('welcomeback:register:query')")
    @GetMapping(value = "/{registerId}")
    public AjaxResult getInfo(@PathVariable("registerId") Long registerId)
    {
        return success(registerBeanService.selectRegisterBeanByRegisterId(registerId));
    }

    /**
     * 新增信息登记记录
     */
    @PreAuthorize("@ss.hasPermi('welcomeback:register:add')")
    @Log(title = "信息登记记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RegisterBean registerBean)
    {
        return toAjax(registerBeanService.insertRegisterBean(registerBean));
    }

    /**
     * 修改信息登记记录
     */
    @PreAuthorize("@ss.hasPermi('welcomeback:register:edit')")
    @Log(title = "信息登记记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RegisterBean registerBean)
    {
        return toAjax(registerBeanService.updateRegisterBean(registerBean));
    }

    /**
     * 删除信息登记记录
     */
    @PreAuthorize("@ss.hasPermi('welcomeback:register:remove')")
    @Log(title = "信息登记记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{registerIds}")
    public AjaxResult remove(@PathVariable Long[] registerIds)
    {
        return toAjax(registerBeanService.deleteRegisterBeanByRegisterIds(registerIds));
    }
}
