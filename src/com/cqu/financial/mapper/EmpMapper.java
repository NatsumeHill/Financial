package com.cqu.financial.mapper;

import java.util.List;

import com.cqu.financial.entity.Emp;

public interface EmpMapper {
	public List<Emp> findAll();
	public boolean addEmp(Emp emp);
	public void delEmp(String empno);
	public Emp findById(String empno);
	public void modifyEmp(Emp emp);
}
