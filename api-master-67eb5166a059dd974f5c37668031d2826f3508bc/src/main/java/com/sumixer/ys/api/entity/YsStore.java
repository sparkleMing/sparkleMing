package com.sumixer.ys.api.entity;

import java.util.Date;

public class YsStore {
    private Integer id;

    private String storeId;

    private String agentId;

    private String storeName;

    private String localtion;

    private Double path;

    private Integer status;

    private Date createTime;

    private Date beginTime;

    private Date endTime;

    private String contactName;

    private String contactPhone;

    private String address;

    private String area;

    private String businessLice;

    private String idcardUp;

    private String idcardDown;

    private Double sendMoney;

    private String openDay;

    private Integer openBeginTime;

    private Integer openEndTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLocaltion() {
        return localtion;
    }

    public void setLocaltion(String localtion) {
        this.localtion = localtion;
    }

    public Double getPath() {
        return path;
    }

    public void setPath(Double path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBusinessLice() {
        return businessLice;
    }

    public void setBusinessLice(String businessLice) {
        this.businessLice = businessLice;
    }

    public String getIdcardUp() {
        return idcardUp;
    }

    public void setIdcardUp(String idcardUp) {
        this.idcardUp = idcardUp;
    }

    public String getIdcardDown() {
        return idcardDown;
    }

    public void setIdcardDown(String idcardDown) {
        this.idcardDown = idcardDown;
    }

    public Double getSendMoney() {
        return sendMoney;
    }

    public void setSendMoney(Double sendMoney) {
        this.sendMoney = sendMoney;
    }

    public String getOpenDay() {
        return openDay;
    }

    public void setOpenDay(String openDay) {
        this.openDay = openDay;
    }

    public Integer getOpenBeginTime() {
        return openBeginTime;
    }

    public void setOpenBeginTime(Integer openBeginTime) {
        this.openBeginTime = openBeginTime;
    }

    public Integer getOpenEndTime() {
        return openEndTime;
    }

    public void setOpenEndTime(Integer openEndTime) {
        this.openEndTime = openEndTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", storeId=").append(storeId);
        sb.append(", agentId=").append(agentId);
        sb.append(", storeName=").append(storeName);
        sb.append(", localtion=").append(localtion);
        sb.append(", path=").append(path);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", address=").append(address);
        sb.append(", area=").append(area);
        sb.append(", businessLice=").append(businessLice);
        sb.append(", idcardUp=").append(idcardUp);
        sb.append(", idcardDown=").append(idcardDown);
        sb.append(", sendMoney=").append(sendMoney);
        sb.append(", openDay=").append(openDay);
        sb.append(", openBeginTime=").append(openBeginTime);
        sb.append(", openEndTime=").append(openEndTime);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        YsStore other = (YsStore) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStoreId() == null ? other.getStoreId() == null : this.getStoreId().equals(other.getStoreId()))
            && (this.getAgentId() == null ? other.getAgentId() == null : this.getAgentId().equals(other.getAgentId()))
            && (this.getStoreName() == null ? other.getStoreName() == null : this.getStoreName().equals(other.getStoreName()))
            && (this.getLocaltion() == null ? other.getLocaltion() == null : this.getLocaltion().equals(other.getLocaltion()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getBeginTime() == null ? other.getBeginTime() == null : this.getBeginTime().equals(other.getBeginTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getBusinessLice() == null ? other.getBusinessLice() == null : this.getBusinessLice().equals(other.getBusinessLice()))
            && (this.getIdcardUp() == null ? other.getIdcardUp() == null : this.getIdcardUp().equals(other.getIdcardUp()))
            && (this.getIdcardDown() == null ? other.getIdcardDown() == null : this.getIdcardDown().equals(other.getIdcardDown()))
            && (this.getSendMoney() == null ? other.getSendMoney() == null : this.getSendMoney().equals(other.getSendMoney()))
            && (this.getOpenDay() == null ? other.getOpenDay() == null : this.getOpenDay().equals(other.getOpenDay()))
            && (this.getOpenBeginTime() == null ? other.getOpenBeginTime() == null : this.getOpenBeginTime().equals(other.getOpenBeginTime()))
            && (this.getOpenEndTime() == null ? other.getOpenEndTime() == null : this.getOpenEndTime().equals(other.getOpenEndTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
        result = prime * result + ((getAgentId() == null) ? 0 : getAgentId().hashCode());
        result = prime * result + ((getStoreName() == null) ? 0 : getStoreName().hashCode());
        result = prime * result + ((getLocaltion() == null) ? 0 : getLocaltion().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getBeginTime() == null) ? 0 : getBeginTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getBusinessLice() == null) ? 0 : getBusinessLice().hashCode());
        result = prime * result + ((getIdcardUp() == null) ? 0 : getIdcardUp().hashCode());
        result = prime * result + ((getIdcardDown() == null) ? 0 : getIdcardDown().hashCode());
        result = prime * result + ((getSendMoney() == null) ? 0 : getSendMoney().hashCode());
        result = prime * result + ((getOpenDay() == null) ? 0 : getOpenDay().hashCode());
        result = prime * result + ((getOpenBeginTime() == null) ? 0 : getOpenBeginTime().hashCode());
        result = prime * result + ((getOpenEndTime() == null) ? 0 : getOpenEndTime().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table ys_store
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        id("id", "id", "INTEGER"),
        storeId("store_id", "storeId", "VARCHAR"),
        agentId("agent_id", "agentId", "VARCHAR"),
        storeName("store_name", "storeName", "VARCHAR"),
        localtion("localtion", "localtion", "VARCHAR"),
        path("path", "path", "DOUBLE"),
        status("status", "status", "INTEGER"),
        createTime("create_time", "createTime", "TIMESTAMP"),
        beginTime("begin_time", "beginTime", "TIMESTAMP"),
        endTime("end_time", "endTime", "TIMESTAMP"),
        contactName("contact_name", "contactName", "VARCHAR"),
        contactPhone("contact_phone", "contactPhone", "VARCHAR"),
        address("address", "address", "VARCHAR"),
        area("area", "area", "VARCHAR"),
        businessLice("business_lice", "businessLice", "VARCHAR"),
        idcardUp("idcard_up", "idcardUp", "VARCHAR"),
        idcardDown("idcard_down", "idcardDown", "VARCHAR"),
        sendMoney("send_money", "sendMoney", "DOUBLE"),
        openDay("open_day", "openDay", "VARCHAR"),
        openBeginTime("open_begin_time", "openBeginTime", "INTEGER"),
        openEndTime("open_end_time", "openEndTime", "INTEGER");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ys_store
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ys_store
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ys_store
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ys_store
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ys_store
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ys_store
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ys_store
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ys_store
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column, String javaProperty, String jdbcType) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ys_store
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.column + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ys_store
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.column + " ASC";
        }
    }
}