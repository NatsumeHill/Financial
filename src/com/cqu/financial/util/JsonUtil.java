package com.cqu.financial.util;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.cqu.financial.entity.BillInOut;
import com.cqu.financial.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	public static JSONArray generateJson(JSONArray categories,List<BillInOut> bills){
		JSONArray dataByFlag = new JSONArray();
		Iterator<BillInOut> iterator = bills.iterator();
		while (iterator.hasNext()) {
			BillInOut bill = iterator.next();
			categories.add(bill.getBillFlag());
			JSONObject object = new JSONObject();
			object.put("value", bill.getBillSumMoney());
			object.put("name", bill.getBillFlag());
			dataByFlag.add(object);
		}
		return dataByFlag;
	}
}
