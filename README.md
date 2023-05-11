# springBoot实现多租户
![https://img.shields.io/badge/springBoot-2.7-green](https://img.shields.io/badge/springBoot-2.7-green)![](https://img.shields.io/badge/mysql-8-green)![](https://img.shields.io/badge/mybatis-3.5.1-green)![](https://img.shields.io/badge/dynamic--datasource-3.2.0-green)

## 项目说明

原文参考我的博客 https://nwjshm.cn/archives/35.html

## 项目结构

| 模块               | 说明               |
| ------------------ | ------------------ |
| `common包`         | 返回信息           |
| `config.webConfig` | 注册拦截器         |
| `demo包`           | 测试数据源切换     |
| `handler.handler`  | 租户切换拦截器     |
| `system包`         | 在线添加数据源信息 |

## 运行项目

1. 下载源码后 先打开`sql`目录下的 `multitenancydemo.sql` 和`multitenancydemo02.sql` 两个脚本添加到数据库 里面 **注意两个脚本两个库**  然后修改yml配置文件的数据地址信息
2. 修改后运行项目即可 默认端口为`8088`

## 接口说明

接口文档地址：https://console-docs.apipost.cn/preview/06e56c446734b3c0/d5633d0e38822971

> 请求前缀：/multitenancy

### 01. 添加数据源

> 请求地址：/sys/dataSource/add
>
> 请求方式：post
>
> 请求类型：json

**请求Body参数**

```javascript
{
  "code": "zuhu1", //数据源编码
  "createBy": "",
  "createTime": "",
  "dbDriver": "com.mysql.jdbc.Driver",//驱动类
  "dbName": "multitenancydemo02",//数据库名称
  "dbPassword": "123456",//数据库密码
  "dbType": "2", //数据库类型 MySQL5.5	1 MYSQL5.7+	2
  "dbUrl": "jdbc:mysql://127.0.0.1:3306/multitenancydemo02?characterEncoding=UTF-8&useUnicode=true&useSSL=false",//数据源地址
  "dbUsername": "root",//用户名
  "id": "",
  "name": "测试数据源", //数据源名称
  "remark": "",//备注
  "sysOrgCode": "",
  "updateBy": "",
  "updateTime": ""
}
```

| 参数名     | 示例值 | 参数类型 | 是否必填 | 参数描述                         |
| ---------- | ------ | -------- | -------- | -------------------------------- |
| code       | -      | String   | 是       | 数据源编码                       |
| createBy   | -      | String   | 是       | -                                |
| createTime | -      | String   | 是       | -                                |
| dbDriver   | -      | String   | 是       | 驱动类                           |
| dbName     | -      | String   | 是       | 数据库名称                       |
| dbPassword | -      | String   | 是       | 密码                             |
| dbType     | -      | String   | 是       | 数据库类型 mysql5.5 1 mysql5.7 2 |
| dbUrl      | -      | String   | 是       | 数据源地址                       |
| dbUsername | -      | String   | 是       | 用户名                           |
| id         | -      | String   | 是       | -                                |
| name       | -      | String   | 是       | 数据源名称                       |
| remark     | -      | String   | 是       | 备注                             |
| sysOrgCode | -      | String   | 是       | -                                |
| updateBy   | -      | String   | 是       | -                                |
| updateTime | -      | String   | 是       | -                                |

### 02.  添加测试数据

> 请求地址：/test/sysUser/add
>
> 请求方式：post
>
> 请求类型：json

#### 请求Header参数

| 参数名  | 示例值 | 参数类型 | 是否必填 | 参数描述                          |
| ------- | ------ | -------- | -------- | --------------------------------- |
| tencode | zuhu1  | String   | 是       | 数据源编码 如果为空默认是主数据源 |

#### 请求Body参数

```javascript
{
    "name": "张三",
    "age":13,
    "phone":"1886679899"
}
```

### 03. 查询测试数据

> 请求地址：/test/sysUser/list
>
> 请求方式：get
>
> 请求类型：无

#### 请求Header参数

| 参数名  | 示例值 | 参数类型 | 是否必填 | 参数描述                          |
| ------- | ------ | -------- | -------- | --------------------------------- |
| tencode | zuhu1  | String   | 是       | 数据源编码 如果为空默认是主数据源 |
