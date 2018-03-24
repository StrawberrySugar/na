package com.zizaihome.api.db.model;

import java.sql.ResultSet;
import java.util.Date;

public class GoodsImgModel extends BaseModel{
	private int id=0;
	private String img="";
	private int goodsId=0;
	private int status=0;
	private Date addTime=new Date();
	@Override
	public BaseModel getModelByRs(ResultSet rs) {
		try {
			this.id=rs.getInt("id");
			this.img=rs.getString("img");
			this.goodsId=rs.getInt("goodsId");
			this.status=rs.getInt("status");
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
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
	
}
