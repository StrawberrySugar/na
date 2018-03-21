package com.zizaihome.api.db.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetMetaDataImpl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coola.jutil.data.DataPage;
import com.coola.jutil.sql.DBEngine;
import com.coola.jutil.sql.DBFactory;
import com.coola.jutil.sql.ResultPage;
import com.zizaihome.api.db.model.BaseModel;
import com.zizaihome.api.utils.LogUtils;
import com.zizaihome.api.utils.ReflectUtils;

public abstract class BaseDao<T extends BaseModel> {
    /**
     * 日志处理类实例
     */
    public static Log logger = LogFactory.getLog(BaseDao.class);
    /**
     * 只读DB引擎
     */
    public static DBEngine readDBEngine = DBFactory.getDBEngine(System.getProperty("api.conf.dir")+"/dbconfig.xml","main-b");
    /**
     * 只写DB引擎
     */
    public static DBEngine writeDBEngine = DBFactory.getDBEngine(System.getProperty("api.conf.dir")+"/dbconfig.xml","main");
    
    /**
     * 常链接的数据库名称
     */
    public static String LOCAL_WRITE_POOL = "main";


    /**
     * 主表名
     */
    protected String tableName;

    /**
     * 主表ID
     */
    protected String tableKeyId;
    
    /**
     * 标记状态位的字段-1删除
     */
    protected String statusColumn;


    /**
     * 具体的实现类
     */
    private Class<T> entityClass;
    
    protected String SELECT = "";
    protected String UPDATE = "";
    
    
    public int insert(T model){
		DBEngine localEngine = DBFactory.getKeepConnectionDBEngine("main");
		StringBuffer sql = new StringBuffer("INSERT INTO `" + tableName + "` (");
		StringBuffer stmts =  new StringBuffer(" values(");
		List<Object> sqlArgs = new ArrayList<Object>();
		Field[] fields = model.getClass().getDeclaredFields();
		for(Field field : fields){
			if(field.getName().equals("serialVersionUID") || field.getName().equals("id")){
				continue;
			}
			if(field.getName().equals("addDate") || field.getName().equals("addTime")){
				sql.append(field.getName()+",");
				stmts.append("now(),");
			}
			
			else if(ReflectUtils.getter(model, field.getName())!=null){
				sql.append("`"+field.getName()+"`,");
				stmts.append("?,");
				sqlArgs.add(ReflectUtils.getter(model, field.getName()));
			}
		}
		
		String sqlstr = sql.toString();
		sqlstr = sqlstr.substring(0, sqlstr.length()-1);
		sqlstr = sqlstr + ")";
		
		String stmtstr = stmts.toString();
		stmtstr = stmtstr.substring(0, stmtstr.length()-1);
		stmtstr = stmtstr + ")";
       
		LogUtils.log.info(sqlstr + stmtstr);
		
		Object[]  paramObj = sqlArgs.toArray();
       int id=0;
       try {
       		String tSql = sqlstr+stmtstr;
            id = localEngine.executeUpdate(tSql, paramObj);
            ResultSet rs = localEngine.executeQuery("select last_insert_id() as id");
            if (rs.next()) {
              id = rs.getInt("id");
            }
       }catch(Exception e) {
          e.printStackTrace();
       } finally {
          localEngine.close();
       }
       return id;
    }
    
    public boolean updateAny(T model){
    	StringBuffer sql = new StringBuffer("update  `"+tableName+"` set ");
		List<Object> sqlArgs = new ArrayList<Object>();
//		Field[] superfields = userModel.getClass().getSuperclass().getDeclaredFields();
		Field[] fields = model.getClass().getDeclaredFields();
		for(Field field : fields){
			if(field.getName().equals("serialVersionUID") 
					|| field.getName().equals("id")
					|| field.getName().equals("addDate")
					|| field.getName().equals("addTime")
					|| field.getName().equals("updateTime")
					|| field.getName().equals("updateDate")){
				continue;
			}
			if(ReflectUtils.getter(model, field.getName())!=null){
				sql.append(" `"+field.getName()+"`=? ,");
				sqlArgs.add(ReflectUtils.getter(model, field.getName()));
			}
		}
		
		String sqlstr = sql.toString();
		sqlstr = sqlstr.substring(0, sqlstr.length()-1);
		sqlstr = sqlstr + " where id=?";
		sqlArgs.add(ReflectUtils.getter(model, "id"));
		LogUtils.log.info(sqlstr);
		LogUtils.log.info(sqlArgs.toString());
		
		Object[]  paramObj = sqlArgs.toArray();
        try {
             return writeDBEngine.executeUpdate(sqlstr, paramObj)>0;
        } catch(Exception e) {
           e.printStackTrace();
        }
        return false;
    }
    
    public boolean delWithStatus(int id){
    	StringBuffer sql = new StringBuffer("update  `"+tableName+"` set "+ statusColumn+"=-1 ");
    	sql.append(" where id=?");
        try {
             return writeDBEngine.executeUpdate(sql.toString(), id)>0;
        } catch(Exception e) {
           e.printStackTrace();
        }
        return false;
    }


    public BaseDao(String tableName, String tableKeyId, String statusColumn) {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
        this.tableName = tableName;
        this.tableKeyId = tableKeyId;
        this.statusColumn = statusColumn;
        this.SELECT = "select * from `"+tableName+"` ";
        this.UPDATE = "update `"+tableName+"` set ";
    }

    /**
     * 获得model集合
     * 
     * @param sql 查询语句
     * @return 返回Model集合
     */
    protected List<T> queryModelList(String sql) {
        List<T> modelList = new ArrayList<T>();
        try {
            ResultSet rs = readDBEngine.executeQuery(sql);
            while (rs.next()) {
                modelList.add((T) entityClass.newInstance().getModelByRs(rs));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            logger.error(sql);
        }
        return modelList;
    }

    /**
     * 获得model集合
     * 
     * @param sql 查询语句
     * @param objs 参数集
     * @return 返回Model集合
     */
    protected List<T> queryModelList(String sql, Object[] paramObjs) {
        List<T> modelList = new ArrayList<T>();
        try {
            ResultSet rs = readDBEngine.executeQuery(sql, paramObjs);
            while (rs.next()) {
                modelList.add((T) entityClass.newInstance().getModelByRs(rs));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            logger.error(sql);
        }
        return modelList;
    }

    /**
     * 获得model集合(扩展了其中的字段)
     * 
     * @param sql 查询语句
     * @param objs 参数集
     * @return 返回Model集合
     */
    protected List<T> queryModelExtList(String sql, Object[] paramObjs) {
        List<T> modelList = new ArrayList<T>();
        try {
            ResultSet rs = readDBEngine.executeQuery(sql, paramObjs);
            while (rs.next()) {
                modelList.add((T) entityClass.newInstance().getModelExtByRs(rs));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return modelList;
    }

    /**
     * 获得model
     * 
     * @param sql 查询语句
     * @param objs 参数集
     * @return 返回Model集合
     */
    protected T queryModel(String sql, Object[] paramObjs) {
        T t = null;
        try {
            ResultSet rs = readDBEngine.executeQuery(sql, paramObjs);
            if (rs.next()) {
                t = (T) entityClass.newInstance().getModelByRs(rs);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return t;
    }
    
    /**
     * 获得model
     * 
     * @param sql 查询语句
     * @param objs 参数集
     * @return 返回Model集合
     */
    protected T queryModel(String sql) {
        T t = null;
        try {
            ResultSet rs = readDBEngine.executeQuery(sql);
            if (rs.next()) {
                t = (T) entityClass.newInstance().getModelByRs(rs);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 按主键查询model
     * 
     * @param id id
     * @return model
     */
    public T findByPrimaryKey(Integer id) {
        String sql = "SELECT * FROM `" + tableName + "` WHERE " + tableKeyId + " = ?";
        Object[] paramObjs = {id};
        List<T> modelList = this.queryModelList(sql, paramObjs);
        if (modelList != null && modelList.size() > 0) {
            return modelList.get(0);
        }
        return null;
    }
    
    /**
     * 按主键删除一条数据
     * 
     * @param id id
     * @return 删除成功返回true,否则返回false
     */
    public boolean deleteByPrimaryKey(Integer id) {
        String sql = "DELETE FROM `" + tableName + "` WHERE " + tableKeyId + " = ?";
        Object[] paramObjs = {id};
        try {
            return writeDBEngine.executeUpdate(sql, paramObjs) > 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获得model集合
     * 
     * @return 返回Model集合
     */
    public List<T> findAll() {
        String sql = "SELECT * FROM `" + tableName+"`";
        if(statusColumn != null && !statusColumn.equals("")){
        	sql += " where "+statusColumn+">=0";
        }
        return queryModelList(sql);
    }


    /**
     * 根据排序获取model集合
     * 
     * @param column 排序字段
     * @param sort 排序类别（asc,desc）
     * @return
     */
    public List<T> findAllBySort(String column, String sort) {
        String sql = "SELECT * FROM `" + tableName+"`";
        if(statusColumn != null && !statusColumn.equals("")){
        	sql += " where "+statusColumn+">=0 ";
        }
        sql += " order by " + column + " " + sort;
        return queryModelList(sql);
    }

    /**
     * 获分页
     * 
     * @param sql 查询条件
     * @param pageSize 分页大小
     * @param pageNo 页码
     * @return 返回分页对象
     */
    protected DataPage<T> findPage(String sql, int pageSize, int pageNo) {
        CachedRowSet rs = null;
        DataPage<T> resultList = null;
        try {
            ResultPage page = readDBEngine.queryPage(sql, pageSize, pageNo);
            rs = page.getRecord();
            List<T> list = new ArrayList<T>();
            while (rs.next()) {
                list.add((T) entityClass.newInstance().getModelByRs(rs));
            }
            if (list.size() > 0) {
                int totalRecord = getTotalRecords(sql);
                resultList = new DataPage<T>(list, totalRecord, pageSize, pageNo);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return resultList;
    }
    
    public List<T> findPageByLastId(int pageSize,int lastId)
    {
    	CachedRowSet rs = null;
        List<T> resultList = null;
        String sql = "select * from `"+ tableName+"`";
        if(lastId > 0 )
        {
        	sql += " where "+tableKeyId+" < "+ lastId;
        }
        if(statusColumn != null && !statusColumn.equals("")){
        	sql += " and "+statusColumn+">=0 ";
        }
        sql += " order by "+tableKeyId+" desc ";
        sql += " limit "+pageSize;
        try {
            rs = readDBEngine.executeQuery(sql);
            resultList = new ArrayList<T>();
            while (rs.next()) {
            	resultList.add((T) entityClass.newInstance().getModelByRs(rs));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        logger.info("sql="+sql);
        return resultList;
    }

    /**
     * 获取总的记录条数
     * 
     * @param sql
     * @return
     */
    public int getTotalRecords(String sql) {
        int count = 0;
        if (StringUtils.isBlank(sql)) {
            sql = "SELECT count(1) as totalCount FROM `" + tableName+"`";
            if(statusColumn != null && !statusColumn.equals("")){
            	sql += " where "+statusColumn+">=0 ";
            }
        } else {
            logger.info("getTotalRecords before:sql=" + sql);
            sql =
                    sql.replace("where", "WHERE").replace("from", "FROM").replace("order", "ORDER")
                            .replace("limit", "LIMIT");
            sql = sql.substring(sql.indexOf("FROM"), sql.length());
            if (sql.contains("ORDER")) {
                sql = sql.substring(0, sql.indexOf("ORDER"));
            }
            if (sql.contains("LIMIT")) {
                sql = sql.substring(0, sql.indexOf("LIMIT"));
            }
            sql = "SELECT  count(1) as totalCount " + sql;
            logger.info("getTotalRecords after:sql=" + sql);
        }
        try {
            ResultSet rs = readDBEngine.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(getColIdxByLabelName("totalCount", rs));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 获分页
     * 
     * @param pageSize 分页大小
     * @param pageNo 页码
     * @return 返回分页对象
     */
    public DataPage<T> findPage(int pageSize, int pageNo) {
        String sql = "SELECT * FROM `" + tableName+"`";
        if(statusColumn != null && !statusColumn.equals("")){
        	sql += " where "+statusColumn+">=0 ";
        }
        sql += " order by "+ tableKeyId +" desc";
        return findPage(sql, pageSize, pageNo);
    }

    /**
     * 根据别名获取所处的位置(由于目前不支持别名的查询，所以写了这个方法)
     * 
     * @return
     */
    public int getColIdxByLabelName(String labelName, ResultSet rs) throws SQLException {

        RowSetMetaDataImpl rowSetMD = (RowSetMetaDataImpl) rs.getMetaData();
        int i = rowSetMD.getColumnCount();
        for (int j = 1; j <= i; j++) {
            String str = rowSetMD.getColumnLabel(j);// 使用别名
            if ((str != null) && (labelName.equalsIgnoreCase(str))) return j;
        }
        throw new SQLException("列名无效：" + labelName);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableKeyId() {
        return tableKeyId;
    }

    public void setTableKeyId(String tableKeyId) {
        this.tableKeyId = tableKeyId;
    }


}
