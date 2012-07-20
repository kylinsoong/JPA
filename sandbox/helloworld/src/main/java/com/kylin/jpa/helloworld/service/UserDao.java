package com.kylin.jpa.helloworld.service;

import com.kylin.jpa.helloworld.po.User;

public interface UserDao {
   User getForUsername(String username);

   void createUser(User user);
}
