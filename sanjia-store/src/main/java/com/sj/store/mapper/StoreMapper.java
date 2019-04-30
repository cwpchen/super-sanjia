package com.sj.store.mapper;

import java.util.List;

import com.sj.common.pojo.Favorite;
import com.sj.common.pojo.Purchase;

public interface StoreMapper {

	public List<String> queryMyStore(String userName) ;

	public Purchase queryMovieById(String favoriteId);

	public Integer deleteStore(Favorite favorite);

	public Integer addStore(Favorite favorite);
}
