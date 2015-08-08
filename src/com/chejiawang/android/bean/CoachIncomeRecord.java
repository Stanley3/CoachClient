package com.chejiawang.android.bean;

public class CoachIncomeRecord {
	private Integer coach_id;
	private Integer student_id;
	private String student_name;
	private Integer course_status;
	private String student_phone;
	private String order_dead_time;
	private Integer student_level;
	private Double order_amount;
	private Integer order_id;
	private String order_freeze_time;
	
	public String getOrder_freeze_time() {
		return order_freeze_time;
	}
	public void setOrder_freeze_time(String order_freeze_time) {
		this.order_freeze_time = order_freeze_time;
	}
	public Integer getCoach_id() {
		return coach_id;
	}
	public void setCoach_id(Integer coach_id) {
		this.coach_id = coach_id;
	}
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public Integer getCourse_status() {
		return course_status;
	}
	public void setCourse_status(Integer course_status) {
		this.course_status = course_status;
	}
	public String getStudent_phone() {
		return student_phone;
	}
	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}
	public String getOrder_dead_time() {
		return order_dead_time;
	}
	public void setOrder_dead_time(String order_dead_time) {
		this.order_dead_time = order_dead_time;
	}
	public Integer getStudent_level() {
		return student_level;
	}
	public void setStudent_level(Integer student_level) {
		this.student_level = student_level;
	}
	public Double getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(Double order_amount) {
		this.order_amount = order_amount;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
}
