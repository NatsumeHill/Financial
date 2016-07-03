package com.cqu.financial.controller;

import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String addBill(Bill bill, HttpSession session) {
		String userId = ((User) session.getAttribute("user")).getUserID();
		try {
			billService.addBill(bill, userId);
		} catch (Exception e) {
			return "404";
		}
		return "addBill";
	}

	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("list");
		modelAndView.addObject("bills", billService.findAll());
		return modelAndView;
	}

	@RequestMapping("goModify")
	public ModelAndView goModify(@RequestParam String billId) {
		Bill bill = billService.findById(billId);
		ModelAndView modelAndView = new ModelAndView("modify");
		modelAndView.addObject("bill", bill);
		return modelAndView;
	}

	@RequestMapping("modifyBill")
	public String modifyBill(Bill bill) {
		if (bill != null)
			billService.modifyBill(bill);
		return "redirect:/bill/list";
	}

	@RequestMapping("delBill")
	public String delBill(@RequestParam String billId) {
		billService.delBill(billId);
		return "redirect:/bill/list";
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
	public void getInOutJson(HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=utf-8");
		// 封装echart需要的JSON数据
		String userId = ((User) session.getAttribute("user")).getUserID();
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.println(billService.findInOut(userId));
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
	public void getTrend(HttpServletRequest request,HttpServletResponse response,HttpSession session){
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
}
