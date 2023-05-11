package com.nwjshm.multitenancy.system.service.impl;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DruidDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwjshm.multitenancy.common.api.vo.Result;
import com.nwjshm.multitenancy.system.domain.SysDataSource;
import com.nwjshm.multitenancy.system.service.ISysDataSourceService;
import com.nwjshm.multitenancy.system.mapper.SysDataSourceMapper;
import com.nwjshm.multitenancy.system.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
* @author admin
* @description 针对表【sys_data_source】的数据库操作Service实现
* @createDate 2023-05-11 09:48:23
*/
@Service
public class SysDataSourceServiceImpl extends ServiceImpl<SysDataSourceMapper, SysDataSource> implements ISysDataSourceService {
    @Autowired
    private DruidDataSourceCreator dataSourceCreator;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DynamicRoutingDataSource dataSource1;
    @Override
    public Result saveDataSource(SysDataSource sysDataSource) {
        try {
          //校验数据源编码是否存在
            long count =  checkDbCode(sysDataSource.getCode());
            if (count>0){
                return Result.error("数据源编码已存在");
            }
            //密码加密
            String dbPassword = sysDataSource.getDbPassword();
            if (StringUtils.isNotBlank(dbPassword)) {
                String encrypt = SecurityUtil.jiami(dbPassword);
                sysDataSource.setDbPassword(encrypt);
            }
            //添加数据库
            boolean result = save(sysDataSource);
            if (result) {
                //动态创建数据源
                addDynamicDataSource(sysDataSource, dbPassword);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.OK("添加成功！");
    }
    /**
     * 根据数据源编码 切换数据源
     * @param tenantId
     */
    @Override
    public void changeDsByTenantId(String tenantId) {
        DynamicDataSourceContextHolder.clear();
        if (!existDsInMemory(tenantId)){
            DynamicDataSourceContextHolder.push(tenantId);//手动切换
        }else{
            SysDataSource sysDataSource = baseMapper.selectOne(new QueryWrapper<SysDataSource>().eq("code", tenantId));
            String encrypt = SecurityUtil.jiemi(sysDataSource.getDbPassword());
            //添加数据源
            addDynamicDataSource(sysDataSource,encrypt);
            DynamicDataSourceContextHolder.push(sysDataSource.getCode());//手动切换

        }
    }

    /**
     * 动态添加数据源 【注册mybatis动态数据源】
     *
     * @param sysDataSource 添加数据源数据对象
     * @param dbPassword    未加密的密码
     */
    private void addDynamicDataSource(SysDataSource sysDataSource, String dbPassword) {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setUrl(sysDataSource.getDbUrl());
        dataSourceProperty.setPassword(dbPassword);
        dataSourceProperty.setDriverClassName(sysDataSource.getDbDriver());
        dataSourceProperty.setUsername(sysDataSource.getDbUsername());
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
        try {
            ds.addDataSource(sysDataSource.getCode(), dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查数据源编码是否存在
     *
     * @param dbCode
     * @return
     */
    private long checkDbCode(String dbCode) {
        QueryWrapper<SysDataSource> qw = new QueryWrapper<>();
        qw.lambda().eq(true,SysDataSource::getCode,dbCode);
        return count(qw);
    }

    /**
     * 当前应用是否已在内存中加载过此数据源
     *
     * @param dsName 数据源名称
     * @return
     */

    private Boolean existDsInMemory(String dsName) {
        return isNotEmpty(dataSource1.getDataSource(dsName));
    }

    /**
     * 判断对象是否为空
     * @param object
     * @return
     */
   private boolean isNotEmpty(Object object) {
        if (object != null && !"".equals(object) && !object.equals("null")) {
            return (true);
        }
        return (false);
    }
}




