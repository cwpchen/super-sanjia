package com.sj.search.util;

import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.DataStream;
import org.frameworkset.elasticsearch.client.ImportBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SearchUtil {
	@Value("${spring.datasource.dbName}")
	private String dbName;
	@Value("${spring.datasource.driver-class-name}")
	private String dbDrive;
	@Value("${spring.datasource.url}")
	private String dbUrl;
	@Value("${spring.datasource.username}")
	private String dbUser;
	@Value("${spring.datasource.password}")
	private String dbPassword;
	@Value("${elasticsearch.rest.indexName}")
	private String EsIndex;
	@Value("${elasticsearch.rest.indexType}")
	private String EsIndexType;
	@Value("${spring.datasource.dbTable}")
	private String dbTableName;
	private final int dbCount=10000;
	private final int esCount=20000;
	
	private ImportBuilder build = ImportBuilder.newInstance();
	
	
	public void importDbConfig(){
		try{
			//清除之前的表数据
			ElasticSearchHelper.getRestClientUtil().dropIndice("EsIndex");
		}catch(Exception e){
			e.printStackTrace();
		}
		//数据源相关配置，可选项，可以在外部启动数据源
		build.setDbName(dbName)
		     .setDbDriver(dbDrive) //数据库驱动程序，必须导入相关数据库的驱动jar包
		     .setDbUrl(dbUrl) //通过useCursorFetch=true启用mysql的游标fetch机制，
		     .setDbUser(dbUser) //否则会有严重的性能隐患，useCursorFetch必须和jdbcFetchSize参数配合使用，否则不会生效
		     .setDbPassword(dbPassword)
		     .setValidateSQL("select 1")
		     .setUsePool(false);
		     
		//指定导入数据的sql语句，必填项，可以设置自己的提取逻辑  
		build.setSql("select * from "+dbTableName);  
		
		// es相关配置 
		build.setIndex(EsIndex)
		.setIndexType(EsIndexType)
		.setRefreshOption(null)//可选项，null表示不实时刷新，importBuilder.setRefreshOption("refresh");表示实时刷新
		.setUseJavaName(true) //可选项,将数据库字段名称转换为java驼峰规范的名称，例如:doc_id -> docId
		.setBatchSize(dbCount)  //可选项,批量导入es的记录数，默认为-1，逐条处理，> 0时批量处理
		.setJdbcFetchSize(esCount);//设置数据库的查询fetchsize，同时在mysql url上设置useCursorFetch=true启用mysql的游标fetch机制，否则会有严重的性能隐患，jdbcFetchSize必须和useCursorFetch参数配合使用，否则不会生效
	}
	
	//执行数据库表数据导入es操作
	public void importOperate(){
		DataStream dataStream = build.builder();
		dataStream.db2es();
	}

	@Test
	public void Demo(String[] args) {
		importDbConfig();
		importOperate();
	}
}
