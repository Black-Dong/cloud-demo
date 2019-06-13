package cn.imust.consumer.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class User {
        //id
        private Long id;
        //用户名
        private String userName;
        //密码
        private String password;
        //姓名
        private String name;
        //年龄
        private Integer age;
        //性别
        private Integer sex;
        //生日
        private Date birthday;
        //创建时间
        private Date created;
        //最后修改时间
        private Date updated;
        //备注
        private String note;

}
