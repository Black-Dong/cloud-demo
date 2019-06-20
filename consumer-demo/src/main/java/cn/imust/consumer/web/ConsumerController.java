package cn.imust.consumer.web;

import cn.imust.consumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "fallback")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

/*
    //1.0
    @Autowired
    private DiscoveryClient discoveryClient;
*/
/*
    //2.0
    @Autowired
    private RibbonLoadBalancerClient client;
*/

    public String fallback(){
        log.error("查询用户信息失败");
        return "不好意思太拥挤了！";
    }

    public String queryByIdFallback(Long id){
        log.error("查询用户信息失败 id:{}",id);
        return "不好意思太拥挤了！";
    }

    @GetMapping("{id}")
    //@HystrixCommand(fallbackMethod = "queryByIdFallback")
    //在类上添加@DefaultProperties(defaultFallback = "fallback")后，再配置某个方法的超时时常
    /*@HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })*/
    @HystrixCommand
    public String queryById(@PathVariable("id") Long id){

/*
        //1.0
        //根据服务id获取实例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("USER-SERVICE");
        //从实例列表中取出具体实例的ip和端口
        ServiceInstance serviceInstance = instances.get(0);
*/
 /*       //2.0
        //随机，轮询，hash        -- 负载均衡器 ribbon
        ServiceInstance serviceInstance = client.choose("USER-SERVICE");
*/
/*
        //1.0 && 2.0 通用
        String url = "http://"+serviceInstance.getHost() + ":" + serviceInstance.getPort()+"/user/" + id;
        System.out.println(url);
*/

        //3.0
        String url = "http://USER-SERVICE/user/"+id;
//        User user = restTemplate.getForObject(url,User.class);
        String user = restTemplate.getForObject(url,String.class);

        return user;
    }
}
