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
@Service
public class SearchService {
	@Autowired
	private TransportClient search;

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

}
