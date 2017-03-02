# 这是什么
提供模板应用, 目前有两个branch:  
+ master: 提供基于spring-mvc/hibernate jpa的示例论坛应用
+ ssh: 提供基本Spring+Struts2+HibernateORM的示例应用

# 安装
## 准备数据库
要运行此应用, 需要先安装MySQL. 新建名为zy_blog的数据库, 并运行`db/`内脚本初始化数据库. 同时, 更改`src/main/resources/application.properties`中数据库用户名的密码, 默认都为root.

## 编译前端
本应用前后端完全分离. 前端使用AngularJS, 后台提供RESTFUL API.

在运行应用之前, 需要先对前端进行编译. 编译前端需要事先安装nodejs与npm. 同时, 本项目使用fabric进行项目的构建, 因此, 也需要安装fabric(可以通过pip安装).

在确保这些软件都安装完毕的基础上, 运行`fab generate`, 即可.

## 编译后端
后端由Java+maven构建, 需要事先安装maven. Windows用户可以运行`mvnw.cmd`安装maven, linux用户可以运行`mvnw`来安装. 安装完毕后, 可以运行`fab start`来快速启动应用用于调试. 或者, 运行`fab build`来构建整个项目. 最张的jar包位于生成的`target`文件夹中. 可以直接通过java来运行.

# 说明
本节描述整个项目的相关技术, 将分别从前台/后台两个角度来说明.

## 目录说明
+ db: 数据库脚本文件夹, 用于初始化数据库
+ frontend: 前端代码
+ src: 后台代码
+ fabfile.py: 项目构建任务定义文件
+ mvnw/mvnw.cmd: maven安装脚本
+ nginx.conf.template: nginx配置模板, 当有额外的nginx服务器时, 可以使用此来优化项目
+ Procfile: 项目运行定义文件, 当部署在heroku上时, 会用到
+ pom.xml: 后端项目配置文件

## 前端
前端使用了fis作为构建工具. 并依赖bower作为外部包管理工具.

前端使用AngularJS实现了一个SPA, 使用REST接口与后端进行交互. 整个项目使用模块化的开发方式, 以提高开发效率和降低工程复杂度.

目录文件说明如下:

+ import: 外部模块, 由bower提供
+ modules: 内部模块
+ views: 非模块化文件

## 后端
后端使用spring-boot作为开发框架. 并且采用了传统的MVC及三层架构模式. 其中, 项目的主要代码位于`src/main/java/zy`中.

+ domain: 定义了DTO相关实体类, 用于前后端通信
+ service: 定义了服务层接口, 实现页面相关逻辑, 其实现位于impl中.
+ web: 定义了web接入层, 实现了REST相关接口
+ support: 使用aop提供了基础架构的支持. 包括
    + 执行流程监控
    + 消息hub

为了最大程度的降低项目复杂度, 项目大量使用了第三方库和服务. 包括

+ spring-security, 提供验证及安全服务
+ spring-mvc, 提供REST接口的支持
+ spring-data-jpa: 提供ORM支持
+ spring-aop: 提供AOP支持
+ 七牛云存储: 提供图片存储服务
