package com.sj.search.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.sj.common.pojo.Movie;
import com.sj.common.pojo.ObjectUtil;
import com.sj.common.pojo.Purchase;
import com.sj.common.vo.MovieDetail;
import com.sj.search.mapper.SearchMapper;

import redis.clients.jedis.JedisCluster;
@Service
public class SearchService {
	@Autowired
	private TransportClient search;
	@Autowired
	private SearchMapper searchMapper;

	/**
	 * 从elasticSearch中查找电影字段名包含filmName的文档
	 * @param filmName
	 * @return
	 */
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
	
	/**
	 * 查找电影名称为movieName的电影信息和购票信息
	 * 先从redis 中拿取信息 如果没有直接到数据库中拿，并且将数据写入到redis
	 * @param filmName
	 * @return
	 */
	@Autowired
	private JedisCluster cluster;
	public MovieDetail getFilmDetail(String movieName) {
		String name = movieName;
		List<Purchase> purchaseL = new ArrayList<Purchase>();
		MovieDetail movieDetail = new MovieDetail();
		Movie movie = new Movie();
		//先将name 转为hash 码，根据hash码查询redis中对应内容
		try {
			String nameHash = "MOVIEINFO"+Integer.toHexString(name.hashCode());
			System.out.println(nameHash);
			// 先从redis查找 如果redis没有则从数据库查找
			String movieInfo = cluster.get(nameHash);
			if(StringUtils.isNotEmpty(movieInfo)) { //说明redis中已经存在该值
				//直接将该json串转化为Object后返回
				movie = ObjectUtil.mapper.readValue(movieInfo, Movie.class);
				System.out.println("go to redis for search movie "+name);
			}else {
				//从数据库查询movieName对应t_movie的表数据
				movie = searchMapper.getMovieInfo(name);
				if(movie != null) {					
					//将movie 转为json 串 
					String value = ObjectUtil.mapper.writeValueAsString(movie);
					//将movieName 对应的电影信息写入到redis
					cluster.set(nameHash, value);
				}
			}			
			//判断数据是否存在			
			if (movie != null) {  //如果存在就继续查询对应movieName对应在t_purchase的表信息
				String purchaseHash ="PURCHASE"+Integer.toHexString(name.hashCode());
				System.out.println(purchaseHash);
				// 先从redis查找 如果redis没有则从数据库查找
				String purchaseInfo = cluster.get(purchaseHash);
				if(StringUtils.isNotEmpty(purchaseInfo)) {//说明redis中已经存在该值
					//直接将json串转化为Objiec后返回
					 JsonNode purchaseData = ObjectUtil.mapper.readTree(purchaseInfo);
					 if(purchaseData.isArray() && purchaseData.size()>0) {
						 purchaseL = ObjectUtil.mapper.readValue(purchaseData.traverse(), 
								 ObjectUtil.mapper.getTypeFactory().
								 constructCollectionType(List.class, Purchase.class));
					 }
					 System.out.println("go to redis for search purchases "+name);
				}else {
					//数据库查询购票信息
					purchaseL = searchMapper.getPurchaseInfo(movieName);
					if(purchaseL != null) {					
						//将movie 转为json 串 
						String value = ObjectUtil.mapper.writeValueAsString(purchaseL);
						//将movieName 对应的电影信息写入到redis
						cluster.set(purchaseHash, value);
					}					
				}				
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
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/*public MovieDetail getFilmDetail(String movieName) {
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
*/
}
