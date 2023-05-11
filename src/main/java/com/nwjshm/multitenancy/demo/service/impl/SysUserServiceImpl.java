package com.nwjshm.multitenancy.demo.service.impl;


import com.nwjshm.multitenancy.demo.entity.SysUser;
import com.nwjshm.multitenancy.demo.mapper.SysUserMapper;
import com.nwjshm.multitenancy.demo.service.ISysUserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 测试表
 * @Author: jeecg-boot
 * @Date:   2023-05-11
 * @Version: V1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
