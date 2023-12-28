
# coelectron(原子核心模块)
![coelectron](https://socialify.git.ci/CyberGlint/coelectron/image?description=1&descriptionEditable=&font=KoHo&forks=1&issues=1&language=1&name=1&owner=1&pattern=Circuit%20Board&pulls=1&stargazers=1&theme=Light)


> 该模块是为了快速构建SpringBoot项目而存在

## 版本说明
- JDK 17
- SpringBoot 3.2.0
- SpringCloud 2023.0.0
- SpringCloudAlibaba 2022.0.0.0

## 使用方法
### 示例项目结构
![image_1.png](https://github.com/CyberGlint/doc/blob/main/Writerside/images/image_1.png?raw=true)



### 项目根依赖 SpringBoot-3.2.0-POM
```Java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.cyberglint.project</groupId>
    <artifactId>SpringBoot-3.2.0</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>
    <modules>
        <module>api</module>
        <module>service</module>
    </modules>


    <parent>
        <groupId>com.cyberglint</groupId>
        <artifactId>coelectron</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
```

### 项目API依赖 api
```Java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.cyberglint.project</groupId>
        <artifactId>SpringBoot-3.2.0</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>api</artifactId>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>


    <dependencies>
        <dependency>
            <groupId>com.cyberglint</groupId>
            <artifactId>coelectron-spring-cloud</artifactId>
        </dependency>
    </dependencies>

</project>
```

### 项目Service依赖 service
```Java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.cyberglint.project</groupId>
        <artifactId>SpringBoot-3.2.0</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>service</artifactId>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>com.cyberglint</groupId>
            <artifactId>coelectron-spring-boot</artifactId>
        </dependency>
        <dependency>
            <groupId>com.cyberglint.project</groupId>
            <artifactId>api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
</project>

```

### JVM参数配置NACOS
```java
-Dspring.cloud.nacos.config.import-check.enabled=false
-Dspring.cloud.bootstrap.enabled=true
-Dspring.cloud.nacos.config.server-addr=http://192.168.1.102:8848
-Dspring.cloud.nacos.discovery.server-addr=http://192.168.1.102:8848
-Dspring.cloud.nacos.config.namespace=c74ca668-eb4f-4da1-9574-0b100ea47f96
-Dspring.cloud.nacos.discovery.namespace=c74ca668-eb4f-4da1-9574-0b100ea47f96
-Dspring.main.allow-bean-definition-overriding=true
-Dspring.application.name=cyberglint-member-application
-Dspring.cloud.nacos.config.file-extension=yml
```