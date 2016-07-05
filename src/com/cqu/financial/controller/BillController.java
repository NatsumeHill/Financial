package com.cqu.financial.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cqu.financial.entity.Bill;

import com.cqu.financial.entity.User;
import com.cqu.financial.service.BillService;

@Controller
@RequestMapping("bill")
public class BillController {
	@Resource
	private BillService billService;

	@RequestMapping("goaddBill")
	public String goaddBill() {
		return "addBill";
	}

	@RequestMapping("addBill")
	@ResponseBody
	public boolean addBill(Bill bill, HttpSession session) {
		String userId = ((User) session.getAttribute("user")).getUserID();
		try {
			billService.addBill(bill, userId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("list");
		modelAndView.addObject("bills", billService.findAll());
		return modelAndView;
	}

	@RequestMapping("selectById")
	@ResponseBody
	public Bill selectById(@RequestParam String billId) {
		Bill bill = billService.findById(billId);
		return bill;
	}

	@RequestMapping("modifyBill")
	@ResponseBody
	public boolean modifyBill(Bill bill) {
		if (bill != null) {
			return billService.modifyBill(bill);
		}
		return false;
	}

	@RequestMapping("delBill")
	@ResponseBody
	public boolean delBill(@RequestParam String billId) {
		return billService.delBill(billId);
	}

	/*
	 * 获取各个消费类型的总消费金额，返回一个数据列表页面
	 */
	/*
	 * @RequestMapping("inout") public ModelAndView inOut() { ModelAndView
	 * modelAndView = new ModelAndView("inoutlist");
	 * modelAndView.addObject("list", billService.findInOut("1", "O",
	 * "billFlag_2")); return modelAndView; }
	 */
	@RequestMapping("goJson")
	public String goJson() {
		return "Testjson";
	}

	/*
	 * 获取各个消费类型的总消费金额，将数据写入response中
	 */
	@RequestMapping("jsonInOut")
	public void getInOutJson(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.println(billService.findInOut(request));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null)
				printWriter.close();
		}
	}

	/**
	 * 获取消费趋势数据
	 */
	@RequestMapping("jsonTrend")
	public void getTrend(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String userId = ((User) session.getAttribute("user")).getUserID();
		String year = request.getParameter("year");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.println(billService.billMonthSum(userId, year));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null)
				printWriter.close();
		}
	}

	/**
	 * 分页查询
	 */
	@RequestMapping("bills")
	public void showBillsByPage(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || "".equals(user)) {
		}
		String userId = user.getUserID();
		billService.selectBillsByPage(userId, request, response);
	}
	
	/**
	 * 跳转到主页面
	 * @return
	 */
	@RequestMapping("main")
	public String goMain(){
		return "main";
	}
	
	/**
	 * 跳转到账单详细页面
	 * @return
	 */
	@RequestMapping("bill-detail")
	public String goDetail(){
		return "bill-detail";
	}
	
	/**
	 * 跳转到分类查看页面
	 * @return
	 */
	@RequestMapping("sort-view")
	public String goSort(){
		return "sort-view";
	}
	
	/**
	 * 跳转到空页面
	 * @return
	 */
	@RequestMapping("block")
	public String goBlock(){
		return "block";
	}
}
