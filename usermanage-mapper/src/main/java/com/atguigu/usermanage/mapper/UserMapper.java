package com.atguigu.usermanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.usermanage.pojo.User;

public interface UserMapper {
    
    public User queryUserById(Long id);

    public Long queryTotal();

    public List<User> queryUserAll();
    
    public int insertUser(User user);
    
    public int updateUser(User user);

    public int deleteUsersByIds(@Param("ids")List<Long> ids);

    public Boolean deleteUserById(Long id);


}
