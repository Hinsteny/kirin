## kirin-g
----

### 技术选型
jdk11, mysql8.0, spring5.1, mybatis3, liquibase, log4j2

### 插件执行

1. `./gradlew update` -> 同步liquibase脚本到数据库
2. `./gradlew mybatisGenerate` -> 生成mybatis层代码

### 构建

`./gradlew build`  编译构建打包项目

### fast start
1. 在IDEA开发编译器中直接执行spring-boot程序即可
  
  ```
  org.kirin.Application
  ```

2. 在命令行中使用spring-boot插件启动程序
  
  ```
  gradle bootRun
  ```

默认加载`application.yml`, 也可以通过命令行参数指定激活环境参数:`--spring.profiles.active=dev`
