package com.sj.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sj.search.util.SearchUtil;

@RestController
public class CreateSearchController {
	
	@Autowired
	private SearchUtil searchUtil;
	@RequestMapping(value="importEs",method= RequestMethod.GET)
	public void importSearch(){
		searchUtil.importDbConfig();
		searchUtil.importOperate();
	}
	
}
