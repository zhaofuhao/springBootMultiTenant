package com.nwjshm.multitenancy.system.service;

import com.nwjshm.multitenancy.common.api.vo.Result;
import com.nwjshm.multitenancy.system.domain.SysDataSource;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【sys_data_source】的数据库操作Service
* @createDate 2023-05-11 09:48:23
*/
public interface ISysDataSourceService extends IService<SysDataSource> {

    /**
     * 添加数据源
     * @param sysDataSource
     * @return
     */
    Result saveDataSource(SysDataSource sysDataSource);


    void changeDsByTenantId(String tenantId);
}
