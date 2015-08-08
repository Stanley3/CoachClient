package com.chejiawang.android.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chejiawang.android.bean.Entity;
import com.chejiawang.android.bean.EvaluationAndOrderInfo;
import com.chejiawang.android.utils.ListEntity;

/**
 * 2015.6.28
 * @author 孙晓雨
 *
 */
public class MyEvalution  extends Entity implements ListEntity {
	
	String total;
	String badEvaluation;
	String goodEvaluation;
	String midEvaluation;
	ArrayList<EvaluationAndOrderInfo> evaluationList;
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getBadEvaluation() {
		return badEvaluation;
	}
	public void setBadEvaluation(String badEvaluation) {
		this.badEvaluation = badEvaluation;
	}
	public String getGoodEvaluation() {
		return goodEvaluation;
	}
	public void setGoodEvaluation(String goodEvaluation) {
		this.goodEvaluation = goodEvaluation;
	}
	public String getMidEvaluation() {
		return midEvaluation;
	}
	public void setMidEvaluation(String midEvaluation) {
		this.midEvaluation = midEvaluation;
	}
	public ArrayList<EvaluationAndOrderInfo> getEvaluationList() {
		return evaluationList;
	}
	public void setEvaluationList(ArrayList<EvaluationAndOrderInfo> evaluationList) {
		this.evaluationList = evaluationList;
	}
	@Override
	public List<?> getList() {
		// TODO Auto-generated method stub
		return evaluationList;
	}
	@Override
	public Map<?, ?> getMap() {
		Map map = new HashMap<Object, Object>();
		map.put("total", total);
		map.put("goodEvaluation", goodEvaluation);
		map.put("midEvaluation", midEvaluation);
		map.put("badEvaluation", badEvaluation);
		return map;
	}
	
}
