package com.cqu.financial.service;

import java.util.List;

import com.cqu.financial.entity.Emp;

public interface EmpService {
	public List<Emp> empList();
	public boolean empAdd(Emp emp);
	public void empDel(String empno);
	public Emp findById(String empno);
	public void modifyEmp(Emp emp);
}
