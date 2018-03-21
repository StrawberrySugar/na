package com.zizaihome.api.service;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.coola.jutil.data.DataPage;
import com.zizaihome.api.db.dao.BaseDao;
import com.zizaihome.api.db.model.BaseModel;

public abstract class BaseService<T extends BaseModel, D extends BaseDao<T>> {
	
	private Class<D> entityClass;
	
	public BaseService(){
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[1];
	}
	
	public int insert(T model){
		Method method;
		try {
			method = entityClass.getMethod("getInstance", null);
		   D d = (D) method.invoke(null, null);
		   return d.insert(model);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
	   return 0;
//		try {
//			return entityClass.newInstance().insert(model);
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return 0;
	}
	
	public boolean updateAny(T model){
		Method method;
		try {
			method = entityClass.getMethod("getInstance", null);
			D d = (D) method.invoke(null, null);
			return d.updateAny(model);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
		return false;		
//		try {
//			return entityClass.newInstance().updateAny(model);
//		} catch (InstantiationException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
	}
	
	public T getModel(int id){
		Method method;
		try {
			method = entityClass.getMethod("getInstance", null);
			D d = (D) method.invoke(null, null);
			return d.findByPrimaryKey(id);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
		return null;
//		try {
//			return entityClass.newInstance().findByPrimaryKey(id);
//		} catch (InstantiationException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
	}
	
	public boolean delete(int id){
		Method method;
		try {
			method = entityClass.getMethod("getInstance", null);
			D d = (D) method.invoke(null, null);
			return d.deleteByPrimaryKey(id);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
		return false;
//		try {
//			return entityClass.newInstance().deleteByPrimaryKey(id);
//		} catch (InstantiationException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
	}
	
	public boolean delWithStatus(int id){
		Method method;
		try {
			method = entityClass.getMethod("getInstance", null);
			D d = (D) method.invoke(null, null);
			return d.delWithStatus(id);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
		return false;
		
//		try {
//			return entityClass.newInstance().delWithStatus(id);
//		} catch (InstantiationException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
	}
	
	public List<T> getAll(){
		Method method;
		try {
			method = entityClass.getMethod("getInstance", null);
			D d = (D) method.invoke(null, null);
			return d.findAll();		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
		return null;
//		try {
//			return entityClass.newInstance().findAll();
//		} catch (InstantiationException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
	}
	
	public List<T> getAllBySort(String column,String sort){
		Method method;
		try {
			method = entityClass.getMethod("getInstance", null);
			D d = (D) method.invoke(null, null);
			return d.findAllBySort(column,sort);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
		return null;
//		try {
//			return entityClass.newInstance().findAllBySort(column,sort);
//		} catch (InstantiationException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
	}
	
	public DataPage<T> getPage(int pageSize, int pageNo){
		Method method;
		try {
			method = entityClass.getMethod("getInstance", null);
			D d = (D) method.invoke(null, null);
			return d.findPage(pageSize, pageNo);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
		return null;
//		try {
//			return entityClass.newInstance().findPage(pageSize, pageNo);
//		} catch (InstantiationException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
	}
	
	public List<T> getPageWithLastId(int pageSize, int lastId){
		Method method;
		try {
			method = entityClass.getMethod("getInstance", null);
			D d = (D) method.invoke(null, null);
			return d.findPageByLastId(pageSize, lastId);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
		return null;
//		try {
//			return entityClass.newInstance().findPageByLastId(pageSize, lastId);
//		} catch (InstantiationException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
	}
	
	public int getTotalRecords(){
		Method method;
		try {
			method = entityClass.getMethod("getInstance", null);
			D d = (D) method.invoke(null, null);
			return d.getTotalRecords("");		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
		return 0;
		
//		try {
//			return entityClass.newInstance().getTotalRecords("");
//		} catch (InstantiationException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return 0;
	}

}
