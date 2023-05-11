package com.nwjshm.multitenancy.config;

import com.nwjshm.multitenancy.handler.TenantDsInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：扫地僧
 * @date ：2023-05-11 10:47
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: webConfig配置
 **/
@Slf4j
@Configuration
public class webConfig implements WebMvcConfigurer {
    @Autowired
    private TenantDsInterceptor tenantDsInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("执行了");
        //注册租户切换数据源拦截器
        registry.addInterceptor(this.tenantDsInterceptor);
    }
}
