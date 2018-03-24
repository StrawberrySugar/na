package com.zizaihome.api.db.dao;

import com.zizaihome.api.db.model.OrderModel;

public class OrderDao extends BaseDao<OrderModel>{
	private static OrderDao instance = new OrderDao("order", "id","status");
    
    public static OrderDao getInstance() {
        return instance;
    }
	
	public OrderDao(String tableName, String tableKeyId,
			String statusColumn) {
		super(tableName, tableKeyId, statusColumn);
	}
}
