## 微服务学习

### eureka使用
* 添加依赖
    * 服务端
    ```
    <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
            </dependency>
    </dependencies>
    ```
    * 客户端
    ```
    <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    ```
* 启动eureka的服务端或客户端(通过注释)
    * 客户端使用@EnableDiscoveryClient(兼容)
    * 服务端使用@EnableEurekaServer(仅限eureka)
* 修改配置 (端口, 服务名称, eureka服务地址)
    ```
        server:
          port: xxxx
        spring:
          application:
            name: xxxxxxx
        eureka:
          client:
            service-url:
              defaultZone: http://eureka_ip:eureka_port/eureka
    ```