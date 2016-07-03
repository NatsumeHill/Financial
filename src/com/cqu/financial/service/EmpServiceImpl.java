package com.cqu.financial.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqu.financial.entity.Emp;
import com.cqu.financial.mapper.EmpMapper;

@Service
public class EmpServiceImpl implements EmpService{
	
	//Spring自动为该类注入了mapper, 
	//Service层中主要是获取到dao层提供的数据并进行一定的逻辑处理
	//这个EmpMapper, 就是由spring配置文件第39行扫描并得到的对象
	@Resource
	EmpMapper mapper;
	public List<Emp> empList() {
		return mapper.findAll();
	}
	public boolean empAdd(Emp emp) {
		if(emp == null) return false;
		
		boolean flag = mapper.addEmp(emp);
		return flag;
	}
	public void empDel(String empno){
		mapper.delEmp(empno);
	}
	public Emp findById(String empno) {
		return mapper.findById(empno);
	}
	public void modifyEmp(Emp emp) {
		mapper.modifyEmp(emp);
	}
}
