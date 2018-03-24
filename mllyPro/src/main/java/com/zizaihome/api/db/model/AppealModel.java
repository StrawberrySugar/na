package com.zizaihome.api.db.model;

import java.sql.ResultSet;
import java.util.Date;

public class AppealModel extends BaseModel{
	private int id=0;
	private Date addTime=new Date();
	private int fromUserId=0;
	private int toUserId=0;
	private int orderId=0;
	private String details="";
	private String returnDetails="";
	@Override
	public BaseModel getModelByRs(ResultSet rs) {
		try {
			this.id=rs.getInt("id");
			this.addTime=rs.getDate("addTime");
			this.fromUserId=rs.getInt("fromUserId");
			this.toUserId=rs.getInt("toUserId");
			this.orderId=rs.getInt("orderId");
			this.details=rs.getString("details");
			this.details=rs.getString("details");
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
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}
	public int getToUserId() {
		return toUserId;
	}
	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getReturnDetails() {
		return returnDetails;
	}
	public void setReturnDetails(String returnDetails) {
		this.returnDetails = returnDetails;
	}
	
	
}

