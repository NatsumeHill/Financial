package com.cqu.financial.entity;

public class BillInOut {
	private String billFlag;
//	private String billFlag2;
	private String billSumMoney;
	private String billType;

	public String getBillFlag() {
		return billFlag;
	}

	public void setBillFlag(String billFlag) {
		this.billFlag = billFlag;
	}


	public String getBillSumMoney() {
		return billSumMoney;
	}

	public void setBillSumMoney(String billSumMoney) {
		this.billSumMoney = billSumMoney;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}
}
