package com.cqu.financial.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cqu.financial.entity.Emp;
import com.cqu.financial.service.EmpService;

//@Controller 告诉Spring容器这个类是一个Controller,
//并将其纳入容器中
@Controller

//这个等下看地址栏就知道什么意思了
@RequestMapping("emp")
public class EmpController {
	
	//从容器中获取到类型为EmpService的对象注入给service
	@Resource
	private EmpService service;
	
	//跟上面的一样, 如果请求为emp/findlist 就会进到这个方法里面来
	@RequestMapping("/findlist")
	public ModelAndView listEmp(HttpServletRequest request){
		//ModelAndView中包含了视图的名字, 也可以在其中设置信息
		//在页面上直接可以使用request.getAttribut("xxxx"); 获取到其中的信息
		ModelAndView model = new ModelAndView("list");
		List<Emp> emps = null;
		if(service.empList().size() != 0){
			emps = service.empList();
		}
		for(Emp e : emps){
			System.out.println(e.getEname());
		}
		
		model.addObject("emps", emps);
		return model;
	}
	
	@RequestMapping("/goadd")
	public String goAddemp(){
		return "add";
	}
	
	
	@RequestMapping("/addemp")
	public ModelAndView addEmp(Emp emp){
		//这里表示重定向到emp/findlist
		ModelAndView model = new ModelAndView("redirect:/emp/findlist");
		service.empAdd(emp);
		return model;
	}
	
//	@RequestMapping("/addemp")
//	public ModelAndView addEmp(Emp emp, HttpServletRequest request){
//		//这里表示重定向到emp/findlist
//		try {
//			request.setCharacterEncoding("utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Emp emp1 = new Emp();
//		emp1.setEname(request.getParameter("ename"));
//		emp1.setJob(request.getParameter("job"));
//		ModelAndView model = new ModelAndView("redirect:/emp/findlist");
//		service.empAdd(emp1);
//		return model;
//	}
	
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public ModelAndView addEmp(@RequestParam String empno){
		//System.out.println(empno);
		ModelAndView model = new ModelAndView("redirect:/emp/findlist");
		service.empDel(empno);
		return model;
	}
	
	//多了一个属性, 表示的是参数提交的方法
	@RequestMapping(value = "/goModify", method = RequestMethod.GET)
	public ModelAndView goModify(@RequestParam String empno){
		//System.out.println(empno);
		Emp emp = service.findById(empno);
		ModelAndView m = new ModelAndView("modify");
		m.addObject("emp", emp);
		return m;
	}
	
	@RequestMapping(value = "/domodify", method = RequestMethod.POST)
	public ModelAndView doModify(Emp emp){
		ModelAndView model = new ModelAndView("redirect:/emp/findlist");
		System.out.println(emp.getEname());
		if(emp != null){
			service.modifyEmp(emp);
		}
		return model;
	}
}
