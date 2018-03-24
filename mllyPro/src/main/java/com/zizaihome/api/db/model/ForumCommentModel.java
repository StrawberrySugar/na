package com.zizaihome.api.db.model;

import java.sql.ResultSet;
import java.util.Date;

public class ForumCommentModel extends BaseModel{
	private int id=0;
	private int status=0;
	private Date addTime=new Date();
	private int toforumId=0;
	private int userId=0;
	private String details="";
	@Override
	public BaseModel getModelByRs(ResultSet rs) {
		try {
			this.id=rs.getInt("id");
			this.addTime=rs.getDate("addTime");
			this.details=rs.getString("details");
			this.status=rs.getInt("status");
			this.userId=rs.getInt("userId");
			this.toforumId=rs.getInt("toforumId");
			this.status=rs.getInt("status");
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getToforumId() {
		return toforumId;
	}
	public void setToforumId(int toforumId) {
		this.toforumId = toforumId;
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
	
	
}
