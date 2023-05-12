package com.ruoyi.common.enums;

/**
 * 监控端状态
 */
public enum MonitorStatus {
    /**
     * 未检测状态
     */
    UNCHECKED,
    /**
     * 成功建立链接
     */
    CONNECTED,
    /**
     * 链接失效
     */
    DISCONNECTED,
    /**
     * 无法建立链接
     */
    UNREACHABLE,
    /**
     * 监控端无法访问或出现异常情况
     */
    ERROR
}
