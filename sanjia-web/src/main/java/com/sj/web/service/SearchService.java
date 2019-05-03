package com.sj.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.sj.common.config.HttpClientService;
import com.sj.common.config.UrlAddr;
import com.sj.common.pojo.ObjectUtil;
import com.sj.common.vo.MovieDetail;

import io.netty.util.internal.StringUtil;

@Service
public class SearchService {
	@Autowired
	private HttpClientService client;

	public List<String> getFilmName(String searchkey) {
		List<String> filmsL = new ArrayList<String>();
		String url = UrlAddr.searchqueryUrl + UrlAddr.searchquerykey01+searchkey;
		try {
			String jsondatas = client.doGet(url);
			JsonNode data = ObjectUtil.mapper.readTree(jsondatas);
			if(data.isArray() && data.size()>0){
				filmsL = ObjectUtil.mapper.readValue(data.traverse(),
						ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, String.class));
			}
			return filmsL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<MovieDetail> getFilmDetail(List<String> movieNameL) {
		List<MovieDetail> movieDetails = new ArrayList<MovieDetail>();
		String url = UrlAddr.searchqueryUrl + UrlAddr.searchquerykey02;
		Map<String,Object> param=new HashMap<String,Object>();
		try {
			for (String movieName : movieNameL) {
				param.put("movieName", movieName);
				String data = client.doGet(url,param);
				if(!StringUtil.isNullOrEmpty(data)) {					
					MovieDetail movie = ObjectUtil.mapper.readValue(data, MovieDetail.class);
					movieDetails.add(movie);
				}
			}
			return movieDetails;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
