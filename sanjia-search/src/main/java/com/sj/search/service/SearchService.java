package com.sj.search.service;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.common.pojo.Movie;
import com.sj.common.pojo.ObjectUtil;
import com.sj.common.pojo.Purchase;
import com.sj.common.vo.MovieDetail;
import com.sj.search.mapper.SearchMapper;
@Service
public class SearchService {
	@Autowired
	private TransportClient search;
	@Autowired
	private SearchMapper searchMapper;

	public List<String> getFilmName(String filmName) {
		try {
			//生成query对象
			MatchQueryBuilder query = QueryBuilders.matchQuery("name", filmName);
			//调用客户端search方法
			SearchResponse response = search.prepareSearch("seemovie").setQuery(query).setFrom(0).setSize(5).get();
			//获取响应中的命中数
			SearchHits hits = response.getHits();
			List<String> filmNameL = new ArrayList<String>();
			for(SearchHit hit : hits) {
				String json = hit.getSourceAsString();
				Movie movie = ObjectUtil.mapper.readValue(json, Movie.class);
				filmNameL.add(movie.getName());
			}
//			System.out.println(filmNameL.toString());
			return filmNameL;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public MovieDetail getFilmDetail(String movieName) {
		String name = movieName;
		List<Purchase> purchaseL = new ArrayList<Purchase>();
		MovieDetail movieDetail = new MovieDetail();
		//从数据库查询movieName对应t_movie的表数据
		Movie movie = searchMapper.getMovieInfo(name);
		//判断数据是否存在
		if (movie != null) {
			//如果存在就继续查询对应movieName对应在t_purchase的表信息
			purchaseL = searchMapper.getPurchaseInfo(movieName);
			//如果存在  封装到MovieDetail中
			if(purchaseL != null && purchaseL.size()>0) {
				movieDetail.setCurrentPage(1);
				movieDetail.setTotalPage(1);
				movieDetail.setMovie(movie);
				if(purchaseL.size()>2) {
					List<Purchase> purchases = purchaseL.subList(0, 2);
					movieDetail.setPurchases(purchases);
				}else {
					movieDetail.setPurchases(purchaseL);
				}
				//返回movieDetail
				return movieDetail;
			}else {
				return null;
			}
		}else {			
			return null;
		}		
	}

}
