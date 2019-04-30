package com.sj.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.sj.common.config.HttpClientService;
import com.sj.common.config.UrlAddr;
import com.sj.common.pojo.Favorite;
import com.sj.common.pojo.ObjectUtil;
import com.sj.common.pojo.Purchase;

public class StoreService {
	@Autowired
	private HttpClientService client;
	/**
	 * 显示用户收藏的电影信息
	 * @param username
	 * @return
	 */
	public List<Purchase> queryStore(String username) {
		String url = UrlAddr.storequeryUrl + UrlAddr.storequerykey01+username;
		try {
			String jsondatas = client.doGet(url);
			JsonNode data = ObjectUtil.mapper.readTree(jsondatas);
			List<Purchase> Purchases = null;
			if(data.isArray() && data.size()>0){
				Purchases = ObjectUtil.mapper.readValue(data.traverse(),
						ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, Purchase.class));
			}
			return Purchases;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 删除用户收藏的购票信息
	 * @param favorite
	 * @return
	 */
	public String deleteStore(Favorite favorite) {
		String url = UrlAddr.storequeryUrl + UrlAddr.storedeletekey02;
		Map<String,Object> param  = new HashMap<String,Object>();
		param.put("username", favorite.getUserName());
		param.put("favoriteId", favorite.getFavoriteId());
		try {
			String jsondata = client.doGet(url, param);
			return jsondata;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 新增购票收藏信息
	 * @param favorite
	 * @return
	 */
	public String addStore(Favorite favorite) {
		String url = UrlAddr.storequeryUrl + UrlAddr.storeaddkey03;
		Map<String,Object> param  = new HashMap<String,Object>();
		param.put("username", favorite.getUserName());
		param.put("favoriteId", favorite.getFavoriteId());
		try {
			String jsondata = client.doGet(url, param);
			return jsondata;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	
	

}
