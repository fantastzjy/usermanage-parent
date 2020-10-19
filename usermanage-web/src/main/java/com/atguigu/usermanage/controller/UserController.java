package com.atguigu.usermanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.usermanage.pojo.User;
import com.atguigu.usermanage.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
     * @RequestMapping("users") public String toUsers(){
     * 
     * return "users"; }
     * 
     * @RequestMapping("page/add") public String toUserAdd(){
     * 
     * return "user-add"; }
     */

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryUserAll() {

        Map<String, Object> map = new HashMap<>();
        // 查询总条数
        Long total = this.userService.queryTotal();
        map.put("total", total);
        // 查询用户列表List<User>
        List<User> users = this.userService.queryUserAll();
        map.put("rows", users);

        return map;
    }

    @RequestMapping("save")
    @ResponseBody
    public Map<String, String> saveUser(@Valid User user) {
        
        Map<String, String> map = new HashMap<>();
        
        /*if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            map.put("status", "500");
            return map;
        }*/
        
        // 调用service方法新增用户信息
        Boolean flag = this.userService.saveUser(user);
        if (flag) {
            // 成功，put200
            map.put("status", "200");
        } else {
            // 失败，put500
            map.put("status", "500");
        }
        return map;
    }
    
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, String> editUser(@Valid User user) {
        
        Map<String, String> map = new HashMap<>();
        
        /*if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            map.put("status", "500");
            return map;
        }*/
        
        // 调用service方法新增用户信息
        Boolean flag = this.userService.editUser(user);
        if (flag) {
            // 成功，put200
            map.put("status", "200");
        } else {
            // 失败，put500
            map.put("status", "500");
        }
        return map;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String, String> deleteUsers(@RequestParam("ids")List<Long> ids){
        Map<String, String> map = new HashMap<>();
        Boolean flag = this.userService.deleteUsersByIds(ids);
        if (flag) {
            map.put("status", "200");
        } else {
            map.put("status", "500");
        }
        return map;
    }
}
