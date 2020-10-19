package com.atguigu.usermanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.usermanage.pojo.User;
import com.atguigu.usermanage.service.UserService;


@Controller
@RequestMapping("rest/user")
public class RestUserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    // @ResponseBody
    public ResponseEntity<User> queryUserById(@PathVariable("id") Long id) {
        if (id < 1) {
            // 参数不合法，响应400
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            // return ResponseEntity.badRequest().body(null);
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        User user = this.userService.queryUserById(id);
        if (user == null) {
            // 响应404
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
            return ResponseEntity.notFound().build();
        }
        // int i = 1/0;
        // 200
        // return ResponseEntity.status(HttpStatus.OK).body(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> queryUserAll() {
        
        Long total = this.userService.queryTotal();

        List<User> users = this.userService.queryUserAll();

        if (CollectionUtils.isEmpty(users)) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", users);
        return ResponseEntity.ok(map);

    }

    @PostMapping
    public ResponseEntity<Void> saveUser(User user, BindingResult result) {

        if (StringUtils.isEmpty(user.getUserName())) {
            return ResponseEntity.badRequest().build();
        }
        Boolean flag = this.userService.saveUser(user);
        if (flag) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(User user) {

        if (user == null || user.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Boolean flag = this.userService.editUser(user);
        if (flag) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam("ids") List<Long> ids) {

        if (ids == null || ids.size() < 1) {
            return ResponseEntity.badRequest().build();
        }
        Boolean flag = this.userService.deleteUsersByIds(ids);
        if (flag) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
