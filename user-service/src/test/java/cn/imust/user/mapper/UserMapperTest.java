package cn.imust.user.mapper;


import cn.imust.user.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
       /* List<User> users = userMapper.selectAll();
        for (User user : users){

            System.out.println(user);
        }*/

        System.out.println(userMapper.selectByPrimaryKey(1L));

    }

}
