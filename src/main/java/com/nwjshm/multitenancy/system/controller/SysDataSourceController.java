package com.nwjshm.multitenancy.system.controller;

import com.nwjshm.multitenancy.common.api.vo.Result;
import com.nwjshm.multitenancy.system.domain.SysDataSource;
import com.nwjshm.multitenancy.system.service.ISysDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：扫地僧
 * @date ：2023-05-11 09:55
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 多数据源管理
 **/
@RestController
@RequestMapping("/sys/dataSource")
public class SysDataSourceController {
    @Resource
    private ISysDataSourceService sysDataSourceService;

    /**
     * 添加多数据源
     * @param sysDataSource
     * @return
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody SysDataSource sysDataSource){
        return sysDataSourceService.saveDataSource(sysDataSource);
    }

}
