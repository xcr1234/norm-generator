package com.example.table.service.impl;

import norm.support.spring.SpringService;
import org.springframework.stereotype.Service;

import com.example.table.dao.UserDao;
import com.example.table.domain.UserDomain;
import com.example.table.service.UserService;

@Service
public class UserServiceImpl extends SpringService<UserDao,UserDomain,Integer> implements UserService{

}
