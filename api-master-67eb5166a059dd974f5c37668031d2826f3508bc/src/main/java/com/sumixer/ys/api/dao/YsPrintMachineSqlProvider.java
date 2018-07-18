package com.sumixer.ys.api.dao;

import com.sumixer.ys.api.entity.YsPrintMachine;
import com.sumixer.ys.api.entity.YsPrintMachineExample.Criteria;
import com.sumixer.ys.api.entity.YsPrintMachineExample.Criterion;
import com.sumixer.ys.api.entity.YsPrintMachineExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class YsPrintMachineSqlProvider {

    public String countByExample(YsPrintMachineExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("ys_print_machine");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(YsPrintMachineExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("ys_print_machine");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(YsPrintMachine record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ys_print_machine");
        
        if (record.getPrintId() != null) {
            sql.VALUES("print_id", "#{printId,jdbcType=VARCHAR}");
        }
        
        if (record.getMachineCode() != null) {
            sql.VALUES("machine_code", "#{machineCode,jdbcType=VARCHAR}");
        }
        
        if (record.getClientId() != null) {
            sql.VALUES("client_id", "#{clientId,jdbcType=VARCHAR}");
        }
        
        if (record.getClientSecret() != null) {
            sql.VALUES("client_secret", "#{clientSecret,jdbcType=VARCHAR}");
        }
        
        if (record.getRefreshToken() != null) {
            sql.VALUES("refresh_token", "#{refreshToken,jdbcType=VARCHAR}");
        }
        
        if (record.getAccessToken() != null) {
            sql.VALUES("access_token", "#{accessToken,jdbcType=VARCHAR}");
        }
        
        if (record.getExpiresIn() != null) {
            sql.VALUES("expires_in", "#{expiresIn,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("`status`", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getStoreId() != null) {
            sql.VALUES("store_id", "#{storeId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(YsPrintMachineExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("print_id");
        sql.SELECT("machine_code");
        sql.SELECT("client_id");
        sql.SELECT("client_secret");
        sql.SELECT("refresh_token");
        sql.SELECT("access_token");
        sql.SELECT("expires_in");
        sql.SELECT("update_time");
        sql.SELECT("`status`");
        sql.SELECT("store_id");
        sql.FROM("ys_print_machine");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        YsPrintMachine record = (YsPrintMachine) parameter.get("record");
        YsPrintMachineExample example = (YsPrintMachineExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("ys_print_machine");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getPrintId() != null) {
            sql.SET("print_id = #{record.printId,jdbcType=VARCHAR}");
        }
        
        if (record.getMachineCode() != null) {
            sql.SET("machine_code = #{record.machineCode,jdbcType=VARCHAR}");
        }
        
        if (record.getClientId() != null) {
            sql.SET("client_id = #{record.clientId,jdbcType=VARCHAR}");
        }
        
        if (record.getClientSecret() != null) {
            sql.SET("client_secret = #{record.clientSecret,jdbcType=VARCHAR}");
        }
        
        if (record.getRefreshToken() != null) {
            sql.SET("refresh_token = #{record.refreshToken,jdbcType=VARCHAR}");
        }
        
        if (record.getAccessToken() != null) {
            sql.SET("access_token = #{record.accessToken,jdbcType=VARCHAR}");
        }
        
        if (record.getExpiresIn() != null) {
            sql.SET("expires_in = #{record.expiresIn,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("`status` = #{record.status,jdbcType=INTEGER}");
        }
        
        if (record.getStoreId() != null) {
            sql.SET("store_id = #{record.storeId,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("ys_print_machine");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("print_id = #{record.printId,jdbcType=VARCHAR}");
        sql.SET("machine_code = #{record.machineCode,jdbcType=VARCHAR}");
        sql.SET("client_id = #{record.clientId,jdbcType=VARCHAR}");
        sql.SET("client_secret = #{record.clientSecret,jdbcType=VARCHAR}");
        sql.SET("refresh_token = #{record.refreshToken,jdbcType=VARCHAR}");
        sql.SET("access_token = #{record.accessToken,jdbcType=VARCHAR}");
        sql.SET("expires_in = #{record.expiresIn,jdbcType=INTEGER}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("`status` = #{record.status,jdbcType=INTEGER}");
        sql.SET("store_id = #{record.storeId,jdbcType=VARCHAR}");
        
        YsPrintMachineExample example = (YsPrintMachineExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(YsPrintMachine record) {
        SQL sql = new SQL();
        sql.UPDATE("ys_print_machine");
        
        if (record.getPrintId() != null) {
            sql.SET("print_id = #{printId,jdbcType=VARCHAR}");
        }
        
        if (record.getMachineCode() != null) {
            sql.SET("machine_code = #{machineCode,jdbcType=VARCHAR}");
        }
        
        if (record.getClientId() != null) {
            sql.SET("client_id = #{clientId,jdbcType=VARCHAR}");
        }
        
        if (record.getClientSecret() != null) {
            sql.SET("client_secret = #{clientSecret,jdbcType=VARCHAR}");
        }
        
        if (record.getRefreshToken() != null) {
            sql.SET("refresh_token = #{refreshToken,jdbcType=VARCHAR}");
        }
        
        if (record.getAccessToken() != null) {
            sql.SET("access_token = #{accessToken,jdbcType=VARCHAR}");
        }
        
        if (record.getExpiresIn() != null) {
            sql.SET("expires_in = #{expiresIn,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("`status` = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getStoreId() != null) {
            sql.SET("store_id = #{storeId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, YsPrintMachineExample example, boolean includeExamplePhrase) {
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