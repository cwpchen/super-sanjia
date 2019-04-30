package com.sj.web.config;

import java.net.HttpCookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sj.common.config.HttpClientService;
import com.sj.common.config.UrlAddr;
import com.sj.common.pojo.ObjectUtil;
import com.sj.common.pojo.User;
import com.sj.common.utils.CookieUtils;
import com.sj.common.vo.SysResult;

public class StoreInterceptor implements HandlerInterceptor {
	
	@Autowired
	private HttpClientService client;
	
	public StoreInterceptor(HttpClientService client){
		this.client = client;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ticket = CookieUtils.getCookieValue(request, "SJ_TICKET");
		if(StringUtils.isNotEmpty(ticket)){ //cookie中有值 用户登录状态
			String url = UrlAddr.userRegistUrl+UrlAddr.userLoginKey04+ticket;
			String jsondata = client.doGet(url);
			SysResult result = ObjectUtil.mapper.readValue(jsondata, SysResult.class);
			String userdata =(String) result.getData();
			if(StringUtils.isNotEmpty(userdata)){
				User user =  ObjectUtil.mapper.readValue(userdata, User.class);
				request.setAttribute("name", user.getName());
				return true;
			}
		}
		response.sendRedirect("login");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
