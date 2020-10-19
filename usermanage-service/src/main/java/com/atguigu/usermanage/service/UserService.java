package com.atguigu.usermanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.usermanage.mapper.UserMapper;
import com.atguigu.usermanage.pojo.User;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Long queryTotal() {
        
        return this.userMapper.queryTotal();
    }

    public List<User> queryUserAll() {
        
        return this.userMapper.queryUserAll();
    }
    
    public void insertUsers(User user1, User user2){
        this.userMapper.insertUser(user1);
        int i=1/0;
        this.userMapper.insertUser(user2);
    }

    public Boolean saveUser(User user) {
        
        return this.userMapper.insertUser(user) == 1;
    }
    
    public Boolean editUser(User user) {
        
        return this.userMapper.updateUser(user) == 1;
    }

    public Boolean deleteUsersByIds(List<Long> ids) {
        
        return this.userMapper.deleteUsersByIds(ids) > 0;
    }

    public User queryUserById(Long id) {
        return this.userMapper.queryUserById(id);
    }

    public Boolean deleteUserById(Long id) {
        return this.userMapper.deleteUserById(id);
    }
    
}
