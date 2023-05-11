package com.nwjshm.multitenancy.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @TableName sys_data_source
 */
@TableName(value ="sys_data_source")
@Data
public class SysDataSource implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 数据源编码
     */

    private String code;
    /**
     * 数据源名称
     */
    private java.lang.String name;
    /**
     * 描述
     */
    private java.lang.String remark;
    /**
     * 数据库类型 MySQL5.5	1 MYSQL5.7+	2
     */
    private java.lang.String dbType;
    /**
     * 驱动类
     */

    private java.lang.String dbDriver;
    /**
     * 数据源地址
     */

    private java.lang.String dbUrl;
    /**
     * 数据库名称
     */

    private java.lang.String dbName;
    /**
     * 用户名
     */

    private java.lang.String dbUsername;
    /**
     * 密码
     */

    private java.lang.String dbPassword;
    /**
     * 创建人
     */

    private java.lang.String createBy;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    private java.util.Date createTime;
    /**
     * 更新人
     */

    private java.lang.String updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")

    private java.util.Date updateTime;
    /**
     * 所属部门
     */
    private java.lang.String sysOrgCode;
}