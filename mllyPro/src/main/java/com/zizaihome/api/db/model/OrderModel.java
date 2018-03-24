package com.zizaihome.api.db.model;

import java.sql.ResultSet;

public class OrderModel extends BaseModel{
	private int id=0;
	private int status=0;
	private int fromGoodsId=0;
	private int toGoodsId=0;
	private int fromUserId=0;
	private int toUserId=0;
	private int type=0;
	private String count="";
	private String address="";
	@Override
	public BaseModel getModelByRs(ResultSet rs) {
		try {
			this.id=rs.getInt("id");
			this.count=rs.getString("count");
			this.status=rs.getInt("status");
			this.fromGoodsId=rs.getInt("fromGoodsId");
			this.toGoodsId=rs.getInt("toGoodsId");
			this.fromGoodsId=rs.getInt("toGoodsId");
			this.toUserId=rs.getInt("toUserId");
			this.address=rs.getString("address");
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getFromGoodsId() {
		return fromGoodsId;
	}
	public void setFromGoodsId(int fromGoodsId) {
		this.fromGoodsId = fromGoodsId;
	}
	public int getToGoodsId() {
		return toGoodsId;
	}
	public void setToGoodsId(int toGoodsId) {
		this.toGoodsId = toGoodsId;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
