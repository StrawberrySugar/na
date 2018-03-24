package com.zizaihome.api.db.dao;

import com.zizaihome.api.db.model.OrderCommentModel;

public class OrderCommentDao extends BaseDao<OrderCommentModel>{
	private static OrderCommentDao instance = new OrderCommentDao("order_comment", "id","status");
    
    public static OrderCommentDao getInstance() {
        return instance;
    }
	
	public OrderCommentDao(String tableName, String tableKeyId,
			String statusColumn) {
		super(tableName, tableKeyId, statusColumn);
	}
}
