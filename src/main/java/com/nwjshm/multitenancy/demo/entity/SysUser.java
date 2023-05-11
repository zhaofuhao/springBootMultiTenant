package com.nwjshm.multitenancy.demo.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 测试表
 * @Author: jeecg-boot
 * @Date:   2023-05-11
 * @Version: V1.0
 */
@Data
@TableName("sys_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)

public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    private String id;
	/**姓名*/
    private String name;
	/**年龄*/

    private Integer age;
	/**手机号*/
    private String phone;
}
