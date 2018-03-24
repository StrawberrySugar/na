package com.zizaihome.api.db.model;

import java.sql.ResultSet;
import java.util.Date;

public class UserApplyModel extends BaseModel{
	private int id=0;
	private int userId=0;
	private int schCardNum=0;
	private String cardImg="";
	private String name="";
	private String phone="";
	private int floorNum=0;
	private String classNmae="";
	private String major="";
	private int type=0;
	private String details="";
	private String returnDetails="";
	private Date addTime=new Date();
	@Override
	public BaseModel getModelByRs(ResultSet rs) {
		try {
			this.id=rs.getInt("id");
			this.cardImg=rs.getString("cardImg");
			this.classNmae=rs.getString("classNmae");
			this.details=rs.getString("details");
			this.floorNum=rs.getInt("floorNum");
			this.major=rs.getString("major");
			this.name=rs.getString("name");
			this.phone=rs.getString("phone");
			this.returnDetails=rs.getString("returnDetails");
			this.type=rs.getInt("type");
			this.schCardNum=rs.getInt("schCardNum");
			this.userId=rs.getInt("userId");
			this.addTime=rs.getDate("addTime");
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSchCardNum() {
		return schCardNum;
	}
	public void setSchCardNum(int schCardNum) {
		this.schCardNum = schCardNum;
	}
	public String getCardImg() {
		return cardImg;
	}
	public void setCardImg(String cardImg) {
		this.cardImg = cardImg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getFloorNum() {
		return floorNum;
	}
	public void setFloorNum(int floorNum) {
		this.floorNum = floorNum;
	}
	public String getClassNmae() {
		return classNmae;
	}
	public void setClassNmae(String classNmae) {
		this.classNmae = classNmae;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
		
}
