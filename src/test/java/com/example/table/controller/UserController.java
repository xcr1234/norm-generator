package com.example.table.controller;

import com.example.table.util.R;
import com.example.table.domain.UserDomain;
import com.example.table.service.UserService;
import norm.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/list")
    public Page<UserDomain> listAll(UserDomain domain, Page<UserDomain> page){
        userService.findAll(domain,page);
        return page;
    }



    @RequestMapping(value = "/update")
    public R update(UserDomain domain){
        userService.update(domain);
        return R.ok();
    }
    @RequestMapping(value = "/insert")
    public R insert(UserDomain domain){
        userService.save(domain);
        return R.ok();
    }

    @RequestMapping(value = "/delete")
    public R delete(UserDomain domain){
        userService.delete(domain);
        return R.ok();
    }


}