package com.yihan.service;

import com.yihan.dao.UserDao;
import com.yihan.dao.UserDaoImpl;
import com.yihan.dao.UserDaoMysqlImpl;
import com.yihan.dao.UserDaoOracleImpl;

public class UserServiceImpl implements UserService{
    private UserDao userDao = new UserDaoOracleImpl();

    //利用set进行动态实现值的注入
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
