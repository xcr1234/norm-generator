package com.example.table.dao;
import norm.CrudDao;
import org.springframework.stereotype.Repository;
import com.example.table.domain.UserDomain;

@Repository
public interface UserDao extends CrudDao<UserDomain , Integer> {

}
