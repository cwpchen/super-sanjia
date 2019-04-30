package com.sj.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.common.pojo.User;
import com.sj.common.utils.CookieUtils;
import com.sj.common.vo.SysResult;
import com.sj.web.service.ipml.UserServiceIpml;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserServiceIpml userService;
	/**
	 * 用户注册前进行用户名是否已存在的验证
	 * 请求方式	GET
	 * @param userName
	 * @return SysResult
	 */
	@RequestMapping(value = "checkUserName", method = RequestMethod.POST)
	@ResponseBody
	public SysResult checkUserNameExists(String userName){
		//调用service访问持久层判断存在
		SysResult result=
				userService.checkUserNameExists(userName);
		return result;
	}
	/**
	 * 用户注册
	 * 请求方式	POST
	 * @param  user
	 * @return SysResult
	 */
	@RequestMapping(value="regist",method = RequestMethod.POST)
	@ResponseBody
	public SysResult doRegist(User user){
		//传递给service封装数据调用持久层处理
		try{
			userService.doRegist(user);
			//新增成功,返回1
			return SysResult.build(1, "success", null);
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(2, "fail", null);
		}
		
	}
	/**
	 * 登录
	 * 登录信息正常 则添加SJ_TICKET 信息
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	@ResponseBody
	public SysResult doLogin(User user,
			HttpServletRequest request,HttpServletResponse response){
		
			String ticket=userService.doLogin(user);
			if(!StringUtils.isEmpty(ticket)){ //如果登录返回不为空
				CookieUtils.setCookie(request, response, "SJ_TICKET", ticket);
				return SysResult.build(1, "success", null);
			}
			else{
				return SysResult.build(2, "fail", null);
			}		
	}
	
}
