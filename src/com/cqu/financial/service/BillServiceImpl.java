package com.cqu.financial.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.cqu.financial.entity.Bill;
import com.cqu.financial.entity.Page;
import com.cqu.financial.entity.TrendView;
import com.cqu.financial.entity.User;
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
	public boolean modifyBill(Bill bill) {
		try {
			billMapper.modifyBill(bill);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
	public boolean delBill(String billId) {
		try {
			billMapper.delBill(billId);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public JSONObject findInOut(HttpServletRequest request) {
		String userId = ((User) request.getSession().getAttribute("user")).getUserID();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String type = request.getParameter("type");
		JSONObject out = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray categories = new JSONArray();
		JSONObject dataByFlag = new JSONObject();
		dataByFlag.put("billFlag_2", JsonUtil.generateJson(new JSONArray(),
				billMapper.findInOut(userId, "O", "billFlag_2", year, month, type)));
		dataByFlag.put("billFlag_1",
				JsonUtil.generateJson(categories, billMapper.findInOut(userId, "支出", "billFlag_1", year, month, type)));
		data.put("categories", categories);
		data.put("data", dataByFlag);
		out.put("out", data);

		// 支出获取
		data = new JSONObject();
		categories = new JSONArray();
		data.put("data",
				JsonUtil.generateJson(categories, billMapper.findInOut(userId, "收入", "billFlag_1", year, month, type)));
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

	@Override
	public void selectBillsByPage(String userId, HttpServletRequest request, HttpServletResponse response) {
		String pageNow = request.getParameter("pageNow");
		Page page = null;
		int totalCount = (int) billMapper.getBillsCount(userId);
		if (pageNow != null) {
			page = new Page(totalCount, Integer.parseInt(pageNow));
		} else {
			page = new Page(totalCount, 1);

		}
		page.setPageSize(8);
		JSONObject out = new JSONObject();
		JSONArray bills = JSONArray
				.fromObject(billMapper.selectBillByPage(userId, page.getStartPos(), page.getPageSize()));
		JSONObject pageJosn = JSONObject.fromObject(page);
		out.put("page", pageJosn);
		out.put("bills", bills);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.println(out);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
