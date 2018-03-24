package com.zizaihome.api.db.dao;

import com.zizaihome.api.db.model.ForumCommentModel;

public class ForumCommentDao extends BaseDao<ForumCommentModel>{
	private static ForumCommentDao instance = new ForumCommentDao("forum_comment", "id","status");
    
    public static ForumCommentDao getInstance() {
        return instance;
    }
	
	public ForumCommentDao(String tableName, String tableKeyId,
			String statusColumn) {
		super(tableName, tableKeyId, statusColumn);
	}
}
