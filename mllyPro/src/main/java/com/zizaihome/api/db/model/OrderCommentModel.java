package com.zizaihome.api.db.model;

import java.sql.ResultSet;
import java.util.Date;

public class OrderCommentModel extends BaseModel{
	private int id=0;
	private int orderId=0;
	private int userId=0;
	private String details="";
	private int score=0;
	private Date addTime=new Date();
	@Override
	public BaseModel getModelByRs(ResultSet rs) {
		try {
			this.id=rs.getInt("id");
			this.addTime=rs.getDate("addTime");
			this.details=rs.getString("details");
			this.orderId=rs.getInt("orderId");
			this.userId=rs.getInt("userId");
			this.score=rs.getInt("score");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
}
