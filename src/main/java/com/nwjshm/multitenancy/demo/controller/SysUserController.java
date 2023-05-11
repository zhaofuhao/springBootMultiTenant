package com.nwjshm.multitenancy.demo.controller;


import java.util.List;
import javax.servlet.http.HttpServletRequest;


import com.nwjshm.multitenancy.common.api.vo.Result;
import com.nwjshm.multitenancy.demo.entity.SysUser;
import com.nwjshm.multitenancy.demo.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



 /**
 * @Description: 测试表
 * @Author: jeecg-boot
 * @Date:   2023-05-11
 * @Version: V1.0
 */

@RestController
@RequestMapping("/test/sysUser")
@Slf4j
public class SysUserController  {
	@Autowired
	private ISysUserService sysUserService;

	 /**
	  * 查询所有的数据
	  * @return
	  */
	 @GetMapping(value = "/list")
	public Result<?> queryPageList() {

		List<SysUser> list = sysUserService.list();
		return Result.OK(list);
	}
	
	/**
	 *   添加
	 *
	 * @param sysUser
	 * @return
	 */

	//@RequiresPermissions("test:sys_user_1:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SysUser sysUser) {
		sysUserService.save(sysUser);
		return Result.OK("添加成功！");
	}
	




}
