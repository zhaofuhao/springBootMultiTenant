package com.nwjshm.multitenancy.handler;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.nwjshm.multitenancy.system.service.ISysDataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：扫地僧
 * @date ：2023-05-11 10:49
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 租户切换拦截器
 **/
@Slf4j
@Component
public class TenantDsInterceptor implements HandlerInterceptor {
    @Autowired
    private ISysDataSourceService sysDataSourceService;
    /**
     * 在请求处理前调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("经过多数据源Interceptor,当前路径是{}", requestURI);
        String tenantId = request.getHeader("tencode");
        //如果tenantId为空，则使用默认数据源
        if (StringUtils.isNotEmpty(tenantId)){
            log.info("拿到的租户编码{}", tenantId);
            //执行切换数据源方法
            sysDataSourceService.changeDsByTenantId(tenantId);
        }
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空当前线程数据源
        DynamicDataSourceContextHolder.clear();
    }
}
