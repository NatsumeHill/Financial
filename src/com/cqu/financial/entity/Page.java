package com.cqu.financial.entity;

/**
 * 分页实体类
 * 
 * @author Frank
 *
 */
public class Page {
	private int pageNow = 1;// 当前页数
	private int pageSize = 4; // 每页显示记录的条数

	private int totalCount; // 总的记录条数

	private int totalPageCount; // 总的页数

	@SuppressWarnings("unused")
	private int startPos; // 开始位置，从0开始

	@SuppressWarnings("unused")
	private boolean isFirst;// 是否是首页

	@SuppressWarnings("unused")
	private boolean hasPre;// 是否有前一页

	@SuppressWarnings("unused")
	private boolean hasNext;// 是否有下一页

	@SuppressWarnings("unused")
	private boolean isLast;// 是否是后一页

	/**
	 * 默认构造函数
	 */
	public Page() {

	}

	/**
	 * 从总记录数和当前页构造
	 * 
	 * @param totalCount
	 * @param pageNow
	 */
	public Page(int totalCount, int pageNow) {
		this.totalCount = totalCount;
		this.pageNow = pageNow;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 取得总页数
	 * @return
	 */
	public int getTotalPageCount() {
		totalPageCount = getTotalCount() / getPageSize();
		return (totalCount % pageSize == 0) ? totalPageCount : totalPageCount + 1;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	/**
	 * 取得选择记录的初始位置
	 * 
	 * @return
	 */
	public int getStartPos() {
		return (pageNow - 1) * pageSize;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	/**
	 * 是否是第一页
	 * 
	 * @return
	 */
	public boolean isFirst() {
		return (pageNow == 1) ? true : false;
	}

	public void setHasFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	/**
	 * 是否有前一页
	 * 
	 * @return
	 */
	public boolean isHasPre() {
		return isFirst() ? false : true;
	}

	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}

	/**
	 * 是否有下一页
	 * 
	 * @return
	 */
	public boolean isHasNext() {
		return isLast() ? false : true;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	/**
	 * 是否是尾页
	 * 
	 * @return
	 */
	public boolean isLast() {
		return (pageNow == getTotalCount()) ? true : false;
	}

	public void setHasLast(boolean isLast) {
		this.isLast = isLast;
	}
}
