package cn.imust.user.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "tb_user")
public class User {
        //id
        @Id
        @KeySql(useGeneratedKeys = true)    //主键 自增,回显
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

        //@Transient    //不是数据库中的数据, 加该注解
}
