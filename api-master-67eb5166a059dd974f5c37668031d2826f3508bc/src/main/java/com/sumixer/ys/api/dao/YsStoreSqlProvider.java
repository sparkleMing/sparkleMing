package com.sumixer.ys.api.dao;

import com.sumixer.ys.api.entity.YsStore;
import com.sumixer.ys.api.entity.YsStoreExample.Criteria;
import com.sumixer.ys.api.entity.YsStoreExample.Criterion;
import com.sumixer.ys.api.entity.YsStoreExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class YsStoreSqlProvider {

    public String countByExample(YsStoreExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("ys_store");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(YsStoreExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("ys_store");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(YsStore record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ys_store");
        
        if (record.getStoreId() != null) {
            sql.VALUES("store_id", "#{storeId,jdbcType=VARCHAR}");
        }
        
        if (record.getAgentId() != null) {
            sql.VALUES("agent_id", "#{agentId,jdbcType=VARCHAR}");
        }
        
        if (record.getStoreName() != null) {
            sql.VALUES("store_name", "#{storeName,jdbcType=VARCHAR}");
        }
        
        if (record.getLocaltion() != null) {
            sql.VALUES("localtion", "#{localtion,jdbcType=VARCHAR}");
        }
        
        if (record.getPath() != null) {
            sql.VALUES("`path`", "#{path,jdbcType=DOUBLE}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("`status`", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getBeginTime() != null) {
            sql.VALUES("begin_time", "#{beginTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getEndTime() != null) {
            sql.VALUES("end_time", "#{endTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getContactName() != null) {
            sql.VALUES("contact_name", "#{contactName,jdbcType=VARCHAR}");
        }
        
        if (record.getContactPhone() != null) {
            sql.VALUES("contact_phone", "#{contactPhone,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getArea() != null) {
            sql.VALUES("area", "#{area,jdbcType=VARCHAR}");
        }
        
        if (record.getBusinessLice() != null) {
            sql.VALUES("business_lice", "#{businessLice,jdbcType=VARCHAR}");
        }
        
        if (record.getIdcardUp() != null) {
            sql.VALUES("idcard_up", "#{idcardUp,jdbcType=VARCHAR}");
        }
        
        if (record.getIdcardDown() != null) {
            sql.VALUES("idcard_down", "#{idcardDown,jdbcType=VARCHAR}");
        }
        
        if (record.getSendMoney() != null) {
            sql.VALUES("send_money", "#{sendMoney,jdbcType=DOUBLE}");
        }
        
        if (record.getOpenDay() != null) {
            sql.VALUES("open_day", "#{openDay,jdbcType=VARCHAR}");
        }
        
        if (record.getOpenBeginTime() != null) {
            sql.VALUES("open_begin_time", "#{openBeginTime,jdbcType=INTEGER}");
        }
        
        if (record.getOpenEndTime() != null) {
            sql.VALUES("open_end_time", "#{openEndTime,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(YsStoreExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("store_id");
        sql.SELECT("agent_id");
        sql.SELECT("store_name");
        sql.SELECT("localtion");
        sql.SELECT("`path`");
        sql.SELECT("`status`");
        sql.SELECT("create_time");
        sql.SELECT("begin_time");
        sql.SELECT("end_time");
        sql.SELECT("contact_name");
        sql.SELECT("contact_phone");
        sql.SELECT("address");
        sql.SELECT("area");
        sql.SELECT("business_lice");
        sql.SELECT("idcard_up");
        sql.SELECT("idcard_down");
        sql.SELECT("send_money");
        sql.SELECT("open_day");
        sql.SELECT("open_begin_time");
        sql.SELECT("open_end_time");
        sql.FROM("ys_store");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        YsStore record = (YsStore) parameter.get("record");
        YsStoreExample example = (YsStoreExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("ys_store");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getStoreId() != null) {
            sql.SET("store_id = #{record.storeId,jdbcType=VARCHAR}");
        }
        
        if (record.getAgentId() != null) {
            sql.SET("agent_id = #{record.agentId,jdbcType=VARCHAR}");
        }
        
        if (record.getStoreName() != null) {
            sql.SET("store_name = #{record.storeName,jdbcType=VARCHAR}");
        }
        
        if (record.getLocaltion() != null) {
            sql.SET("localtion = #{record.localtion,jdbcType=VARCHAR}");
        }
        
        if (record.getPath() != null) {
            sql.SET("`path` = #{record.path,jdbcType=DOUBLE}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("`status` = #{record.status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getBeginTime() != null) {
            sql.SET("begin_time = #{record.beginTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getEndTime() != null) {
            sql.SET("end_time = #{record.endTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getContactName() != null) {
            sql.SET("contact_name = #{record.contactName,jdbcType=VARCHAR}");
        }
        
        if (record.getContactPhone() != null) {
            sql.SET("contact_phone = #{record.contactPhone,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.SET("address = #{record.address,jdbcType=VARCHAR}");
        }
        
        if (record.getArea() != null) {
            sql.SET("area = #{record.area,jdbcType=VARCHAR}");
        }
        
        if (record.getBusinessLice() != null) {
            sql.SET("business_lice = #{record.businessLice,jdbcType=VARCHAR}");
        }
        
        if (record.getIdcardUp() != null) {
            sql.SET("idcard_up = #{record.idcardUp,jdbcType=VARCHAR}");
        }
        
        if (record.getIdcardDown() != null) {
            sql.SET("idcard_down = #{record.idcardDown,jdbcType=VARCHAR}");
        }
        
        if (record.getSendMoney() != null) {
            sql.SET("send_money = #{record.sendMoney,jdbcType=DOUBLE}");
        }
        
        if (record.getOpenDay() != null) {
            sql.SET("open_day = #{record.openDay,jdbcType=VARCHAR}");
        }
        
        if (record.getOpenBeginTime() != null) {
            sql.SET("open_begin_time = #{record.openBeginTime,jdbcType=INTEGER}");
        }
        
        if (record.getOpenEndTime() != null) {
            sql.SET("open_end_time = #{record.openEndTime,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("ys_store");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("store_id = #{record.storeId,jdbcType=VARCHAR}");
        sql.SET("agent_id = #{record.agentId,jdbcType=VARCHAR}");
        sql.SET("store_name = #{record.storeName,jdbcType=VARCHAR}");
        sql.SET("localtion = #{record.localtion,jdbcType=VARCHAR}");
        sql.SET("`path` = #{record.path,jdbcType=DOUBLE}");
        sql.SET("`status` = #{record.status,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("begin_time = #{record.beginTime,jdbcType=TIMESTAMP}");
        sql.SET("end_time = #{record.endTime,jdbcType=TIMESTAMP}");
        sql.SET("contact_name = #{record.contactName,jdbcType=VARCHAR}");
        sql.SET("contact_phone = #{record.contactPhone,jdbcType=VARCHAR}");
        sql.SET("address = #{record.address,jdbcType=VARCHAR}");
        sql.SET("area = #{record.area,jdbcType=VARCHAR}");
        sql.SET("business_lice = #{record.businessLice,jdbcType=VARCHAR}");
        sql.SET("idcard_up = #{record.idcardUp,jdbcType=VARCHAR}");
        sql.SET("idcard_down = #{record.idcardDown,jdbcType=VARCHAR}");
        sql.SET("send_money = #{record.sendMoney,jdbcType=DOUBLE}");
        sql.SET("open_day = #{record.openDay,jdbcType=VARCHAR}");
        sql.SET("open_begin_time = #{record.openBeginTime,jdbcType=INTEGER}");
        sql.SET("open_end_time = #{record.openEndTime,jdbcType=INTEGER}");
        
        YsStoreExample example = (YsStoreExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(YsStore record) {
        SQL sql = new SQL();
        sql.UPDATE("ys_store");
        
        if (record.getStoreId() != null) {
            sql.SET("store_id = #{storeId,jdbcType=VARCHAR}");
        }
        
        if (record.getAgentId() != null) {
            sql.SET("agent_id = #{agentId,jdbcType=VARCHAR}");
        }
        
        if (record.getStoreName() != null) {
            sql.SET("store_name = #{storeName,jdbcType=VARCHAR}");
        }
        
        if (record.getLocaltion() != null) {
            sql.SET("localtion = #{localtion,jdbcType=VARCHAR}");
        }
        
        if (record.getPath() != null) {
            sql.SET("`path` = #{path,jdbcType=DOUBLE}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("`status` = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getBeginTime() != null) {
            sql.SET("begin_time = #{beginTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getEndTime() != null) {
            sql.SET("end_time = #{endTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getContactName() != null) {
            sql.SET("contact_name = #{contactName,jdbcType=VARCHAR}");
        }
        
        if (record.getContactPhone() != null) {
            sql.SET("contact_phone = #{contactPhone,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.SET("address = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getArea() != null) {
            sql.SET("area = #{area,jdbcType=VARCHAR}");
        }
        
        if (record.getBusinessLice() != null) {
            sql.SET("business_lice = #{businessLice,jdbcType=VARCHAR}");
        }
        
        if (record.getIdcardUp() != null) {
            sql.SET("idcard_up = #{idcardUp,jdbcType=VARCHAR}");
        }
        
        if (record.getIdcardDown() != null) {
            sql.SET("idcard_down = #{idcardDown,jdbcType=VARCHAR}");
        }
        
        if (record.getSendMoney() != null) {
            sql.SET("send_money = #{sendMoney,jdbcType=DOUBLE}");
        }
        
        if (record.getOpenDay() != null) {
            sql.SET("open_day = #{openDay,jdbcType=VARCHAR}");
        }
        
        if (record.getOpenBeginTime() != null) {
            sql.SET("open_begin_time = #{openBeginTime,jdbcType=INTEGER}");
        }
        
        if (record.getOpenEndTime() != null) {
            sql.SET("open_end_time = #{openEndTime,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, YsStoreExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}