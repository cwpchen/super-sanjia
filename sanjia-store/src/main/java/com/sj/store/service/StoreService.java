package com.sj.store.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.common.pojo.Favorite;
import com.sj.common.pojo.Purchase;
import com.sj.store.mapper.StoreMapper;

@Service
public class StoreService {
	@Autowired
	private StoreMapper storeMapper;


	/**
	 * 查询用户的收藏电影购票信息
	 * @param name
	 * @return
	 */
	public List<Purchase> queryStore(String name) {	
		List<String> favoriteIdL = new ArrayList<String>();
		List<Purchase> purchaseL = new ArrayList<Purchase>();
		Integer index = 0;
		favoriteIdL = storeMapper.queryMyStore(name);
		for (String favoriteId : favoriteIdL) {
			Purchase purchase = storeMapper.queryMovieById(favoriteId);
			purchaseL.add(index++, purchase);		
		}
		return purchaseL;
	}

	/**
	 * 删除一条用户收藏信息
	 * @param favorite
	 * @return
	 */

	public String deleteStore(Favorite favorite) {

		return storeMapper.deleteStore(favorite).toString();
	}

	public String addStore(Favorite favorite) {
		// TODO Auto-generated method stub
		return storeMapper.addStore(favorite).toString();
	}
}
