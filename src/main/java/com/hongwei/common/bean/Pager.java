package com.hongwei.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 */
public class Pager<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 查询列表 */
	private List<T> list;
	
	/** 单页条数 */
	private Integer pageSize;

	/** 总记录数 */
	private Long count;
	
	/** 开始查询条数 */
	private Long index;
	
	/** 当前页数 */
	private Integer pageNo;
	
	/** 总页数 */
	private Long pageNum;

	/** 排序 */
	private String orderBy;
	
	public Pager() {
		this.pageSize = 15;
		this.pageNo = 1;
		changeIndex();
	}

	public Pager(Integer pageSize,Integer pageNo) {
		if(pageSize==null){
			this.pageSize = 15;
		}else{
			this.pageSize = pageSize;
		}
		if(pageNo==null){
			this.pageNo = 1;
		}else{
			this.pageNo = pageNo;
		}
		changeIndex();
	}
	
	private void changeIndex(){
		this.index = this.pageSize*(this.pageNo-1L);
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.index = this.pageSize*(this.pageNo-1L);
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.pageNum = (count%15)!=0?(count/15+1):(count/15);
		this.count = count;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		this.index = this.pageSize*(this.pageNo-1L);
	}

	public Long getPageNum() {
		return pageNum;
	}

	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
