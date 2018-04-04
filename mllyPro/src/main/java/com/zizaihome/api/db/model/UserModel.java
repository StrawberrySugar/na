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
	private int schCardNum=0;
	private String cardImg="";
	private String name="";
	private String phone="";
	private int floorNum=0;
	private String className="";
	private String major="";
	private String details="";
	private String returnDetails="";
	
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
			this.schCardNum=rs.getInt("schCardNum");
			this.cardImg=rs.getString("cardImg");
			this.name=rs.getString("name");
			this.phone=rs.getString("phone");
			this.floorNum=rs.getInt("floorNum");
			this.className=rs.getString("className");
			this.major=rs.getString("major");
			this.details=rs.getString("details");
			this.returnDetails=rs.getString("returnDetails");
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


	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
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
