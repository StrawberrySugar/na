package com.zizaihome.api.db.model;

import java.sql.ResultSet;
import java.util.Date;

public class UserModel extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id=0;
	private String userName="";
	private String passWord="";
	private Date addTime=new Date();
	private String birth="";
	private String address="";
	private String sign="";
	private String count="";
	private String headImg="";
	private int type=0;
	
	@Override
	public BaseModel getModelByRs(ResultSet rs) {
		try {
			this.id=rs.getInt("id");
			this.userName=rs.getString("userName");
			this.passWord=rs.getString("passWord");
			this.addTime=rs.getDate("addTime");
			this.birth=rs.getString("birth");
			this.address=rs.getString("address");
			this.sign=rs.getString("sign");
			this.count=rs.getString("count");
			this.headImg=rs.getString("headImg");
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	
	
}
