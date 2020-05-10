package com.plj.back.Mapper;

import com.plj.back.Entities.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.core.annotation.Order;

@Mapper
@Order(1)
public interface UserMapper {
    @Select("select * from `user`"+ "where `userName`=#{param1}")
    User findByUsername(String username);

    @Insert("insert into `user`(`userName`,`password`,`salt`)" + "values(#{username},#{password},#{salt})")
    Integer insertUser(User user);
}
