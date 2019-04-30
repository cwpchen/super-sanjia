package com.sj.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sj.common.pojo.Favorite;
import com.sj.common.pojo.Purchase;
import com.sj.store.service.StoreService;

@RestController
@RequestMapping("store")
public class StoreController {

	@Autowired
	private StoreService storeService;
	
	/**
	 * 查询用户的收藏电影购票信息
	 * @param name
	 * @return
	 */
	@RequestMapping("/query/{name}")
	public List<Purchase> queryStore(@PathVariable String name){
		return  storeService.queryStore(name);
	}
	/**
	 * 删除购票收藏信息
	 * @param favorite
	 * @return
	 */
	@RequestMapping("delete")
	public String deleteStore(Favorite favorite){
		return storeService.deleteStore(favorite);
	}
	
	/**
	 * 新增购票收藏信息
	 * @param favorite
	 * @return
	 */
	@RequestMapping("add")
	public String addStore(Favorite favorite){
		return storeService.addStore(favorite);
	}
	
}
