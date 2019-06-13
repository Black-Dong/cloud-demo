package cn.imust.consumer.web;

import cn.imust.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id){

        //根据服务id获取实例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("USER-SERVICE");
        //从实例列表中取出具体实例的ip和端口
        ServiceInstance serviceInstance = instances.get(0);
        String url = "http://"+serviceInstance.getHost() + ":" + serviceInstance.getPort()+"/user/" + id;

        User user = restTemplate.getForObject(url,User.class);

        return user;
    }
}
