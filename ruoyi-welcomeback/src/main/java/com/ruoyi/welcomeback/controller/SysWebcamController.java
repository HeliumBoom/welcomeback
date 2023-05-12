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
import com.ruoyi.welcomeback.domain.SysWebcam;
import com.ruoyi.welcomeback.service.ISysWebcamService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 监控端Controller
 * 
 * @author Helium
 * @date 2023-05-02
 */
@RestController
@RequestMapping("/welcomeback/webcam")
public class SysWebcamController extends BaseController
{
    @Autowired
    private ISysWebcamService sysWebcamService;

    /**
     * 查询监控端列表
     */
    @PreAuthorize("@ss.hasPermi('welcomeback:webcam:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysWebcam sysWebcam)
    {
        startPage();
        List<SysWebcam> list = sysWebcamService.selectSysWebcamList(sysWebcam);
        return getDataTable(list);
    }

    /**
     * 导出监控端列表
     */
    @PreAuthorize("@ss.hasPermi('welcomeback:webcam:export')")
    @Log(title = "监控端", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysWebcam sysWebcam)
    {
        List<SysWebcam> list = sysWebcamService.selectSysWebcamList(sysWebcam);
        ExcelUtil<SysWebcam> util = new ExcelUtil<SysWebcam>(SysWebcam.class);
        util.exportExcel(response, list, "监控端数据");
    }

    /**
     * 获取监控端详细信息
     */
    @PreAuthorize("@ss.hasPermi('welcomeback:webcam:query')")
    @GetMapping(value = "/{monitorId}")
    public AjaxResult getInfo(@PathVariable("monitorId") Long monitorId)
    {
        return success(sysWebcamService.selectSysWebcamByMonitorId(monitorId));
    }

    /**
     * 新增监控端
     */
    @PreAuthorize("@ss.hasPermi('welcomeback:webcam:add')")
    @Log(title = "监控端", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysWebcam sysWebcam)
    {
        return toAjax(sysWebcamService.insertSysWebcam(sysWebcam));
    }

    /**
     * 修改监控端
     */
    @PreAuthorize("@ss.hasPermi('welcomeback:webcam:edit')")
    @Log(title = "监控端", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysWebcam sysWebcam)
    {
        return toAjax(sysWebcamService.updateSysWebcam(sysWebcam));
    }

    /**
     * 删除监控端
     */
    @PreAuthorize("@ss.hasPermi('welcomeback:webcam:remove')")
    @Log(title = "监控端", businessType = BusinessType.DELETE)
	@DeleteMapping("/{monitorIds}")
    public AjaxResult remove(@PathVariable Long[] monitorIds)
    {
        return toAjax(sysWebcamService.deleteSysWebcamByMonitorIds(monitorIds));
    }
}
