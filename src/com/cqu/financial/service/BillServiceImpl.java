package com.cqu.financial.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqu.financial.entity.Bill;
import com.cqu.financial.entity.TrendView;
import com.cqu.financial.mapper.BillMapper;
import com.cqu.financial.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class BillServiceImpl implements BillService {

	@Resource
	private BillMapper billMapper;

	@Override
	public boolean addBill(Bill bill, String userId) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(d);
		bill.setBillDate(dateNowStr);
		UUID uuid = UUID.randomUUID();
		// uuid转换为字符串时会包含四个"-"，所以长度为36
		bill.setBillId(uuid.toString().replaceAll("-", ""));
		bill.setUserId(userId);
		try {
			billMapper.addBill(bill);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("添加失败");
			return false;
		}
	}

	@Override
	public List<Bill> findAll() {
		try {
			List<Bill> bills = billMapper.findAll();
			return bills;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void modifyBill(Bill bill) {
		try {
			billMapper.modifyBill(bill);
		} catch (Exception e) {
			return;
		}

	}

	@Override
	public Bill findById(String billId) {
		try {
			Bill bill = billMapper.findById(billId);
			return bill;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void delBill(String billId) {
		try {
			billMapper.delBill(billId);
		} catch (Exception e) {
			return;
		}

	}

	@Override
	public JSONObject findInOut(String userId) {
		JSONObject out = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray categories = new JSONArray();
		JSONObject dataByFlag = new JSONObject();
		dataByFlag.put("billFlag_2",
				JsonUtil.generateJson(new JSONArray(), billMapper.findInOut(userId, "O", "billFlag_2")));
		dataByFlag.put("billFlag_1",
				JsonUtil.generateJson(categories, billMapper.findInOut(userId, "O", "billFlag_1")));
		data.put("categories", categories);
		data.put("data", dataByFlag);
		out.put("out", data);

		// 支出获取
		data = new JSONObject();
		categories = new JSONArray();
		data.put("data", JsonUtil.generateJson(categories, billMapper.findInOut(userId, "I", "billFlag_1")));
		data.put("categories", categories);

		out.put("in", data);
		return out;
	}

	@Override
	public JSONObject billMonthSum(String userId, String year) {
		Iterator<TrendView> iterator = billMapper.billMonthSum(userId, year).iterator();
		JSONArray month = new JSONArray();
		JSONArray money = new JSONArray();
		while (iterator.hasNext()) {
			TrendView trendView = iterator.next();
			month.add(trendView.getMonth());
			money.add(trendView.getSumMoney());
		}
		JSONObject data = new JSONObject();
		data.put("month", month);
		data.put("money", money);
		return data;
	}
}
