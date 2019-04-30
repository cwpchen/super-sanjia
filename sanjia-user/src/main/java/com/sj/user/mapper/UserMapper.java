package com.sj.user.mapper;

import com.sj.common.pojo.User;

public interface UserMapper {

	Integer checkExists(String name);

	Integer doRegist(User user);

	User login(User user);



}
