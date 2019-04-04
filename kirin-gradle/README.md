## kirin-kirin-gradle
----
Spring-boot为基础的web服务架构, 以apache-dubbo作为RPC技术实现, 然后再配以nacos为注册中心;


### 技术选型
jdk8, mysql8.0, spring5.1, mybatis3, liquibase, log4j2, apache-dubbo:2.7.1, rocketmq4.4.0, nacos:1.0.0

### 模块划分

1. common: 基础类, 通用工具类等
2. api: RPC-service facade
3. service: web-restful service provider and RPC service provider
4. consumer: web-restful service provider and RPC service consumer
 

### 插件执行

1. `./gradlew update` -> 同步liquibase脚本到数据库
2. `./gradlew mybatisGenerate` -> 生成mybatis层代码

### 构建

`./gradlew build`  编译构建打包项目

### fast start
1. 在IDEA开发编译器中直接执行spring-boot程序即可
  
  ```
  1) 服务提供者: org.kirin.service.Application
  2) 服务消费者: org.kirin.ConsumerApplication
  ```

2. 在命令行中使用spring-boot插件启动程序
  
  ```
  gradle bootRun
  ```

默认加载`application.yml`, 也可以通过命令行参数指定激活环境参数:`--spring.profiles.active=dev`
