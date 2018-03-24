package com.zizaihome.api.db.model;

import java.sql.ResultSet;
import java.util.Date;

public class GoodsModel extends BaseModel{
	
	private int id=0;
	private String name="";
	private int userId=0;
	private String indexImg="";
	private int worth=5;
	private Date addTime=new Date();
	private String details="";
	private int type=0;
	private String story="";
	@Override
	public BaseModel getModelByRs(ResultSet rs) {
		try {
			this.id=rs.getInt("id");
			this.addTime=rs.getDate("addTime");
			this.details=rs.getString("details");
			this.userId=rs.getInt("userId");
			this.type=rs.getInt("type");
			this.story=rs.getString("story");
			this.worth=rs.getInt("worth");
			this.name=rs.getString("story");
			this.indexImg=rs.getString("indexImg");;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getIndexImg() {
		return indexImg;
	}
	public void setIndexImg(String indexImg) {
		this.indexImg = indexImg;
	}
	public int getWorth() {
		return worth;
	}
	public void setWorth(int worth) {
		this.worth = worth;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	
	
}
