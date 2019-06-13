package cn.imust.consumer.web;

import cn.imust.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer")
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

    @GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id){

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

//        String url = "http://"+serviceInstance.getHost() + ":" + serviceInstance.getPort()+"/user/" + id;
//        System.out.println(url);

        String url = "http://USER-SERVICE/user/"+id;
        User user = restTemplate.getForObject(url,User.class);

        return user;
    }
}
