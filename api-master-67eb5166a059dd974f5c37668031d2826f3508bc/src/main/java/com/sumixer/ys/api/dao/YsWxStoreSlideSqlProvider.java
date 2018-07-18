package com.sumixer.ys.api.dao;

import com.sumixer.ys.api.entity.YsWxStoreSlide;
import com.sumixer.ys.api.entity.YsWxStoreSlideExample.Criteria;
import com.sumixer.ys.api.entity.YsWxStoreSlideExample.Criterion;
import com.sumixer.ys.api.entity.YsWxStoreSlideExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class YsWxStoreSlideSqlProvider {

    public String countByExample(YsWxStoreSlideExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("ys_wx_store_slide");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(YsWxStoreSlideExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("ys_wx_store_slide");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(YsWxStoreSlide record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ys_wx_store_slide");
        
        if (record.getSlideId() != null) {
            sql.VALUES("slide_id", "#{slideId,jdbcType=VARCHAR}");
        }
        
        if (record.getSlideUrl() != null) {
            sql.VALUES("slide_url", "#{slideUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getYsStoreId() != null) {
            sql.VALUES("ys_store_id", "#{ysStoreId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(YsWxStoreSlideExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("slide_id");
        sql.SELECT("slide_url");
        sql.SELECT("update_time");
        sql.SELECT("ys_store_id");
        sql.FROM("ys_wx_store_slide");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        YsWxStoreSlide record = (YsWxStoreSlide) parameter.get("record");
        YsWxStoreSlideExample example = (YsWxStoreSlideExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("ys_wx_store_slide");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getSlideId() != null) {
            sql.SET("slide_id = #{record.slideId,jdbcType=VARCHAR}");
        }
        
        if (record.getSlideUrl() != null) {
            sql.SET("slide_url = #{record.slideUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getYsStoreId() != null) {
            sql.SET("ys_store_id = #{record.ysStoreId,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("ys_wx_store_slide");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("slide_id = #{record.slideId,jdbcType=VARCHAR}");
        sql.SET("slide_url = #{record.slideUrl,jdbcType=VARCHAR}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("ys_store_id = #{record.ysStoreId,jdbcType=VARCHAR}");
        
        YsWxStoreSlideExample example = (YsWxStoreSlideExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(YsWxStoreSlide record) {
        SQL sql = new SQL();
        sql.UPDATE("ys_wx_store_slide");
        
        if (record.getSlideId() != null) {
            sql.SET("slide_id = #{slideId,jdbcType=VARCHAR}");
        }
        
        if (record.getSlideUrl() != null) {
            sql.SET("slide_url = #{slideUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getYsStoreId() != null) {
            sql.SET("ys_store_id = #{ysStoreId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, YsWxStoreSlideExample example, boolean includeExamplePhrase) {
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