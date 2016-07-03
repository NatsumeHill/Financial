package com.cqu.financial.service;

import java.util.List;

import com.cqu.financial.entity.Bill;

import net.sf.json.JSONObject;

public interface BillService {
	public boolean addBill(Bill bill,String userId);

	public List<Bill> findAll();

	public void modifyBill(Bill bill);

	public Bill findById(String billId);

	public void delBill(String billId);

	public JSONObject findInOut(String userId);

	public JSONObject billMonthSum(String userId, String year);

}
