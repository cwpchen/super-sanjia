package com.sj.common.config;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
public class ESConfig {
	@Value("${spring.es.nodes:null}")
	private String nodes;
	@Value("${spring.es.clusterName:null}")
	private String clusterName;
	@Bean
	public TransportClient esInit(){
		try{
			Settings setting=Settings.builder()
					.put("cluster.name",clusterName).build();
			TransportClient client=new PreBuiltTransportClient(setting);
			//添加节点信息,连接集群操作es
			String[] node=nodes.split(",");
			for (String hostAndPort : node) {
				//解析ip地址,解析port端口使用
				String host=hostAndPort.split(":")[0];
				int port=Integer.parseInt(hostAndPort.split(":")[1]);
				InetSocketTransportAddress address=
				new InetSocketTransportAddress(
						InetAddress.getByName(host),port);
				client.addTransportAddress(address);
			}
			return client;
		}catch(Exception e){
			System.out.println("由于es配置导致TransportClient无法使用");
			return null;
			
		}
		
		
	}
}
