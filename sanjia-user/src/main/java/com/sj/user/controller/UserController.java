package com.sj.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sj.user.service.ipml.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 用户注册前进行用户名是否已存在的验证
	 * @param userName
	 * @return 返回1 表示已存在 不能进行注册
	 * 		        返回0 表示不存在 可以进行注册
	 */
	@RequestMapping("checkUserName/{userName}")
	public Integer checkUserName(@PathVariable 
			String userName){
		return userService.checkUserName(userName);
	}
	
}
