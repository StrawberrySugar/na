package com.zizaihome.api.db.model;

import java.sql.ResultSet;
import java.util.Date;

public class GoodsApplyModel extends BaseModel{
	private int id=0;
	private int goodsId=0;
	private String details="";
	private Date addTime=new Date();
	private int type=0;
	private String returnDetails="";
	private int userId=0;
	private int status=0;
	@Override
	public BaseModel getModelByRs(ResultSet rs) {
		try {
			this.id=rs.getInt("id");
			this.goodsId=rs.getInt("goodsId");
			this.userId=rs.getInt("userId");
			this.details=rs.getString("details");
			this.returnDetails=rs.getString("returnDetails");
			this.status=rs.getInt("status");
			this.addTime=rs.getDate("addTime");
			this.type=rs.getInt("type");
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
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getReturnDetails() {
		return returnDetails;
	}
	public void setReturnDetails(String returnDetails) {
		this.returnDetails = returnDetails;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
