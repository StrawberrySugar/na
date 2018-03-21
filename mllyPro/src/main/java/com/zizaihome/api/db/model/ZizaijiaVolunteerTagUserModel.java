package com.zizaihome.api.db.model;

import java.sql.ResultSet;
import com.zizaihome.api.db.model.BaseModel;
import java.sql.SQLException;
import java.util.Date;

/**
 * 这是zizaijia_volunteer_tag_user的数据传输对象直接与表对应,只允许在dao与service之间传递<br>
 */
public  class ZizaijiaVolunteerTagUserModel  extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 属性:id<br>
     */
    private Integer id = 0;
    /**
     * 属性:volunteerId<br>
     */
    private Integer volunteerId = 0;
    /**
     * 属性:templeId<br>
     */
    private Integer templeId = 0;
    /**
     * 属性:addDate<br>
     */
    private Date addDate = new Date();
    /**
     * 属性:status<br>
     */
    private Integer status = 0;
    /**
     * 属性:tagId<br>
     */
    private Integer tagId = 0;

    /**
     *  默认构造方法,构造一个默认的ZizaijiaVolunteerTagUserModel对象
     */
    public ZizaijiaVolunteerTagUserModel(){
    }
    public ZizaijiaVolunteerTagUserModel getModelByRs(ResultSet rs) {
    		try {
    			this.id = rs.getInt("id");
    			this.volunteerId = rs.getInt("volunteerId");
    			this.templeId = rs.getInt("templeId");
    			this.addDate = rs.getDate("addDate");
    			this.status = rs.getInt("status");
    			this.tagId = rs.getInt("tagId");
    		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return this;
    }



    /**
     * 设置属性id<br>
     * @param id 待设置的属性id的值
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取属性id<br>
     * @return 属性id的值
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置属性volunteerId<br>
     * @param volunteerId 待设置的属性volunteerId的值
     */
    public void setVolunteerId(Integer volunteerId) {
        this.volunteerId = volunteerId;
    }

    /**
     * 获取属性volunteerId<br>
     * @return 属性volunteerId的值
     */
    public Integer getVolunteerId() {
        return volunteerId;
    }

    /**
     * 设置属性templeId<br>
     * @param templeId 待设置的属性templeId的值
     */
    public void setTempleId(Integer templeId) {
        this.templeId = templeId;
    }

    /**
     * 获取属性templeId<br>
     * @return 属性templeId的值
     */
    public Integer getTempleId() {
        return templeId;
    }

    /**
     * 设置属性addDate<br>
     * @param addDate 待设置的属性addDate的值
     */
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    /**
     * 获取属性addDate<br>
     * @return 属性addDate的值
     */
    public Date getAddDate() {
        return addDate;
    }

    /**
     * 设置属性status<br>
     * @param status 待设置的属性status的值
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取属性status<br>
     * @return 属性status的值
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置属性tagId<br>
     * @param tagId 待设置的属性tagId的值
     */
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    /**
     * 获取属性tagId<br>
     * @return 属性tagId的值
     */
    public Integer getTagId() {
        return tagId;
    }



}
