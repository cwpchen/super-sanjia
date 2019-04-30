package com.sj.user.service.ipml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.user.mapper.UserMapper;
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	/**
	 * 用户注册前进行用户名是否已存在的验证
	 * @param userName
	 * @return 返回1 表示已存在 不能进行注册
	 * 		        返回0 表示不存在 可以进行注册
	 */
	public Integer checkUserName(String userName) {
		
		return userMapper.checkExists(userName);
	}

}
