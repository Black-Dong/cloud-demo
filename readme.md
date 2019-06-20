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
* 启动eureka的服务端或客户端(通过注释在启动类上)
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
### ribbon使用(负载优化)
* 在`eureka`服务的`调用方`可以使用 `ribbon` 对负载进行优化(原理使用随机,轮询,hash 默认是轮询)
    * 导入依赖
    ```
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
    </dependency>
    ```
    * 启动类的RestTemplate上注解(3.0 前两个版本请看源代码)
    ```
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    ```
    * Controller
    ```
    String url = "http://USER-SERVICE/user/"+id;
    User user = restTemplate.getForObject(url,User.class);
    ```
### hystrix使用
 * 导入依赖
    ```
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    </dependency>
    ```
 * 启动类上添加注解
    * @EnableCircuitBreaker   //服务熔断,hystrix
 * 在controller的方法上(只能给某个方法添加逻辑)
    ```
    @HystrixCommand(fallbackMethod = "queryByIdFallback",commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
        })
    ```
    * execution.isolation.thread.timeoutInMilliseconds自定义超时时长
    * queryByIdFallback方法的返回值和参数列表必须和被@HystrixCommand修饰的方法返回值一致
 * 在controller上(fallback空参)
    * @DefaultProperties(defaultFallback = "fallback")

 
 
# @SpringCloudApplication
```
可以替代
@EnableCircuitBreaker   //线程隔离服务降级,容错 hystrix
@SpringBootApplication  //springboot启动类
@EnableDiscoveryClient  //微服务客户端
```
