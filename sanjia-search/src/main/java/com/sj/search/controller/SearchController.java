package com.sj.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sj.common.pojo.ObjectUtil;
import com.sj.search.service.SearchService;

@RestController
@RequestMapping("search")
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("associate/{searchkey}")
	public String getFilmName(@PathVariable String searchkey){
		List<String> filmNameL = new ArrayList<String>();
		filmNameL =searchService.getFilmName(searchkey);
//		model.addAttribute("filmNames", filmNameL);
		try {
			String filmNames = ObjectUtil.mapper.writeValueAsString(filmNameL);
			
				return filmNames;
						
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	
	

}
