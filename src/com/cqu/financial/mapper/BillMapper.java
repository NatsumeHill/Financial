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
	
	/**
	 * 按条件查询收入支出
	 * @param userId
	 * @param billType
	 * @param billFlag
	 * @param year
	 * @param month
	 * @param type
	 * @return
	 */
	public List<BillInOut> findInOut(@Param("userId") String userId, @Param("billType") String billType,
			@Param("billFlag") String billFlag, @Param("year") String year, @Param("month") String month,
			@Param("type") String type);

	public List<TrendView> billMonthSum(@Param("userId") String userId, @Param("year") String year);

	/**
	 * 根据分页进行查询
	 * 
	 * @param userId
	 * @param startPoss
	 * @param pageSize
	 * @return
	 */
	public List<Bill> selectBillByPage(@Param("userId") String userId, @Param("startPos") Integer startPoss,
			@Param("pageSize") Integer pageSize);

	/**
	 * 获取当前用户总记录数
	 * 
	 * @param userId
	 * @return
	 */
	public long getBillsCount(@Param("userId") String userId);

}
