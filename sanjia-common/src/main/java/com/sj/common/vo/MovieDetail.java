package com.sj.common.vo;

import java.util.List;

import com.sj.common.pojo.Movie;
import com.sj.common.pojo.Purchase;


public class MovieDetail {
	//总页数
		private Integer totalPage;
		//当前页数
		private Integer currentPage;
		
		private Movie movie;
		//查询分页结果
		private List<Purchase> purchases;
		public Integer getTotalPage() {
			return totalPage;
		}
		public void setTotalPage(Integer totalPage) {
			this.totalPage = totalPage;
		}
		public Integer getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(Integer currentPage) {
			this.currentPage = currentPage;
		}
		public Movie getMovie() {
			return movie;
		}
		public void setMovie(Movie movie) {
			this.movie = movie;
		}
		public List<Purchase> getPurchases() {
			return purchases;
		}
		public void setPurchases(List<Purchase> purchases) {
			this.purchases = purchases;
		}

}
