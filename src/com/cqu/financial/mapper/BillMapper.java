package com.cqu.financial.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqu.financial.entity.Bill;
import com.cqu.financial.entity.BillInOut;
import com.cqu.financial.entity.TrendView;

public interface BillMapper {
	public int addBill(Bill bill);

	public List<Bill> findAll();

	public void modifyBill(Bill bill);

	public Bill findById(String billId);

	public void delBill(String billId);

	public List<BillInOut> findInOut(@Param("userId") String userId, @Param("billType") String billType,
			@Param("billFlag") String billFlag);

	public List<TrendView> billMonthSum(@Param("userId") String userId, @Param("year") String year);

}
