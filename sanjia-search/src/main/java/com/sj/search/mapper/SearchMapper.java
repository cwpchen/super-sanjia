package com.sj.search.mapper;

import java.util.List;

import com.sj.common.pojo.Movie;
import com.sj.common.pojo.Purchase;

public interface SearchMapper {

	Movie getMovieInfo(String name);

	List<Purchase> getPurchaseInfo(String movieName);

}
