package com.chejiawang.android.bean;

public class DisplayStudentDepositReocrd {
	private Integer student_deposit_id;
	private String deposit_date;
	private String deposit_address;//暂时为null
	private Double deposit_amount;
	private Integer deposit_way;
	private Integer deposit_type;
	
	public Integer getStudent_deposit_id() {
		return student_deposit_id;
	}
	public void setStudent_deposit_id(Integer student_deposit_id) {
		this.student_deposit_id = student_deposit_id;
	}
	public String getDeposit_date() {
		return deposit_date;
	}
	public void setDeposit_date(String deposit_date) {
		this.deposit_date = deposit_date;
	}
	public String getDeposit_address() {
		return deposit_address;
	}
	public void setDeposit_address(String deposit_address) {
		this.deposit_address = deposit_address;
	}
	public Double getDeposit_amount() {
		return deposit_amount;
	}
	public void setDeposit_amount(Double deposit_amount) {
		this.deposit_amount = deposit_amount;
	}
	public Integer getDeposit_way() {
		return deposit_way;
	}
	public void setDeposit_way(Integer deposit_way) {
		this.deposit_way = deposit_way;
	}
	public Integer getDeposit_type() {
		return deposit_type;
	}
	public void setDeposit_type(Integer deposit_type) {
		this.deposit_type = deposit_type;
	}
}
