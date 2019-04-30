package com.sj.common.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 每个configuration的代码,都对应xml一部分配置
 * @author admin
 */

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
@Configuration
//@ConfigurationProperties(prefix="spring.redis.cluster")
public class RedisClusterConfig {
	@Value("${spring.redis.cluster.nodes:null}")
	private String nodes;
	@Value("${spring.redis.cluster.maxTotal:0}")
	private Integer maxTotal;
	@Value("${spring.redis.cluster.maxIdle:0}")
	private Integer maxIdle;
	@Value("${spring.redis.cluster.minIdle:0}")
	private Integer minIdle;

	@Bean//初始化方法构造一个jedisCluster对象
	public JedisCluster redisInit(){
		try{
			Set<HostAndPort> set=new HashSet<HostAndPort>();
			//"10.9.39.13:8000,10.9.39.13:8001"
			String[] node = nodes.split(",");
			for (String hostAndPort : node) {
				//"10.9.39.13:8000",解析ip和port
				String host=hostAndPort.split(":")[0];
				int port=Integer.parseInt
						(hostAndPort.split(":")[1]);
				set.add(new HostAndPort(host,port));
			}
			//利用其它属性,编写config对象
			JedisPoolConfig config=new JedisPoolConfig();
			config.setMaxTotal(maxTotal);
			config.setMaxIdle(maxIdle);
			config.setMinIdle(minIdle);
			return new JedisCluster(set,config);
		}catch(Exception e){
			//说明构造过程出现了一些问题,一般是因为没有提供
			//redis相关配置
			System.out.println("由于配置文件没有正确生成jedisCluster对象");
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
