package com.cqu.financial.entity;

public class Bill {
	private String billId;
	private String userId;
	private String billFlag_1;
	private String billFlag_2;
	private String billDate;
	private String billMoney;
	private String billType;
	private String isHide;

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBillFlag_1() {
		return billFlag_1;
	}

	public void setBillFlag_1(String billFlag_1) {
		this.billFlag_1 = billFlag_1;
	}

	public String getBillFlag_2() {
		return billFlag_2;
	}

	public void setBillFlag_2(String billFlag_2) {
		this.billFlag_2 = billFlag_2;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(String billMoney) {
		this.billMoney = billMoney;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getIsHide() {
		return isHide;
	}

	public void setIsHide(String isHide) {
		this.isHide = isHide;
	}

}
