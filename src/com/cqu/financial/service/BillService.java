package com.cqu.financial.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqu.financial.entity.Bill;

import net.sf.json.JSONObject;

public interface BillService {
	public boolean addBill(Bill bill,String userId);

	public List<Bill> findAll();

	public boolean modifyBill(Bill bill);

	public Bill findById(String billId);

	public boolean delBill(String billId);

	public JSONObject findInOut(HttpServletRequest request);

	public JSONObject billMonthSum(String userId, String year);

	/**
	 * 分页查找bill
	 * @param userId
	 * @param request
	 * @return
	 */
	public void selectBillsByPage(String userId,HttpServletRequest request,HttpServletResponse response);
}
