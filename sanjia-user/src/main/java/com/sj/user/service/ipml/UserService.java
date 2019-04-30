package com.sj.user.service.ipml;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sj.common.pojo.ObjectUtil;
import com.sj.common.pojo.User;
import com.sj.common.utils.MD5Util;
import com.sj.common.vo.SysResult;
import com.sj.user.mapper.UserMapper;

import redis.clients.jedis.JedisCluster;
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
	public Integer userSave(User user) {
		try{
			String password = MD5Util.md5(user.getPass());
			user.setPass(password);
			return userMapper.doRegist(user);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
	}
	
	@Autowired
	private JedisCluster cluster;
	public String userLogin(User user) {
		
		String password = MD5Util.md5(user.getPass());
		user.setPass(password);
		User exiUser = userMapper.login(user);
		if(exiUser!=null){ 
			
			String exitTicket = cluster.get(exiUser.getName());
//			String exiTicket = cluster.get("SJ_TICKET");
			if(StringUtils.isNotEmpty(exitTicket)){
				cluster.del(exitTicket);
			}
			
			String key = "SJ_TICKET"+user.getName()+System.currentTimeMillis(); 			
			String jsondata;
			try {
				jsondata = ObjectUtil.mapper.writeValueAsString(exiUser);
				String ticket=MD5Util.md5(key);
				cluster.set(exiUser.getName(), ticket);
				cluster.set(ticket, jsondata);
				return ticket;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
			
		}else{  
			return "";
		}
		
	}
	public SysResult isExiTicket(String ticket) {
		
		Long lastTime = cluster.ttl(ticket);
		if(lastTime<5*60 && lastTime>0){
			cluster.expire(ticket, (int)(lastTime+10*60));
		}
		String jsondata = cluster.get(ticket);
		return new SysResult(200, "sucess", jsondata);
	}

}
