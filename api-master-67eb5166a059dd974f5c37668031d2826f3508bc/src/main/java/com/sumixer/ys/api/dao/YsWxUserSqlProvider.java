package com.sumixer.ys.api.dao;

import com.sumixer.ys.api.entity.YsWxUser;
import com.sumixer.ys.api.entity.YsWxUserExample.Criteria;
import com.sumixer.ys.api.entity.YsWxUserExample.Criterion;
import com.sumixer.ys.api.entity.YsWxUserExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class YsWxUserSqlProvider {

    public String countByExample(YsWxUserExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("ys_wx_user");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(YsWxUserExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("ys_wx_user");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(YsWxUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ys_wx_user");
        
        if (record.getOpenid() != null) {
            sql.VALUES("openid", "#{openid,jdbcType=VARCHAR}");
        }
        
        if (record.getNickname() != null) {
            sql.VALUES("nickname", "#{nickname,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            sql.VALUES("sex", "#{sex,jdbcType=INTEGER}");
        }
        
        if (record.getLanguage() != null) {
            sql.VALUES("`language`", "#{language,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            sql.VALUES("city", "#{city,jdbcType=VARCHAR}");
        }
        
        if (record.getProvince() != null) {
            sql.VALUES("province", "#{province,jdbcType=VARCHAR}");
        }
        
        if (record.getCountry() != null) {
            sql.VALUES("country", "#{country,jdbcType=VARCHAR}");
        }
        
        if (record.getHeadimgurl() != null) {
            sql.VALUES("headimgurl", "#{headimgurl,jdbcType=VARCHAR}");
        }
        
        if (record.getUnionid() != null) {
            sql.VALUES("unionid", "#{unionid,jdbcType=VARCHAR}");
        }
        
        if (record.getWxAppid() != null) {
            sql.VALUES("wx_appid", "#{wxAppid,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(YsWxUserExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("openid");
        sql.SELECT("nickname");
        sql.SELECT("sex");
        sql.SELECT("`language`");
        sql.SELECT("city");
        sql.SELECT("province");
        sql.SELECT("country");
        sql.SELECT("headimgurl");
        sql.SELECT("unionid");
        sql.SELECT("wx_appid");
        sql.FROM("ys_wx_user");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        YsWxUser record = (YsWxUser) parameter.get("record");
        YsWxUserExample example = (YsWxUserExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("ys_wx_user");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getOpenid() != null) {
            sql.SET("openid = #{record.openid,jdbcType=VARCHAR}");
        }
        
        if (record.getNickname() != null) {
            sql.SET("nickname = #{record.nickname,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            sql.SET("sex = #{record.sex,jdbcType=INTEGER}");
        }
        
        if (record.getLanguage() != null) {
            sql.SET("`language` = #{record.language,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            sql.SET("city = #{record.city,jdbcType=VARCHAR}");
        }
        
        if (record.getProvince() != null) {
            sql.SET("province = #{record.province,jdbcType=VARCHAR}");
        }
        
        if (record.getCountry() != null) {
            sql.SET("country = #{record.country,jdbcType=VARCHAR}");
        }
        
        if (record.getHeadimgurl() != null) {
            sql.SET("headimgurl = #{record.headimgurl,jdbcType=VARCHAR}");
        }
        
        if (record.getUnionid() != null) {
            sql.SET("unionid = #{record.unionid,jdbcType=VARCHAR}");
        }
        
        if (record.getWxAppid() != null) {
            sql.SET("wx_appid = #{record.wxAppid,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("ys_wx_user");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("openid = #{record.openid,jdbcType=VARCHAR}");
        sql.SET("nickname = #{record.nickname,jdbcType=VARCHAR}");
        sql.SET("sex = #{record.sex,jdbcType=INTEGER}");
        sql.SET("`language` = #{record.language,jdbcType=VARCHAR}");
        sql.SET("city = #{record.city,jdbcType=VARCHAR}");
        sql.SET("province = #{record.province,jdbcType=VARCHAR}");
        sql.SET("country = #{record.country,jdbcType=VARCHAR}");
        sql.SET("headimgurl = #{record.headimgurl,jdbcType=VARCHAR}");
        sql.SET("unionid = #{record.unionid,jdbcType=VARCHAR}");
        sql.SET("wx_appid = #{record.wxAppid,jdbcType=VARCHAR}");
        
        YsWxUserExample example = (YsWxUserExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(YsWxUser record) {
        SQL sql = new SQL();
        sql.UPDATE("ys_wx_user");
        
        if (record.getOpenid() != null) {
            sql.SET("openid = #{openid,jdbcType=VARCHAR}");
        }
        
        if (record.getNickname() != null) {
            sql.SET("nickname = #{nickname,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            sql.SET("sex = #{sex,jdbcType=INTEGER}");
        }
        
        if (record.getLanguage() != null) {
            sql.SET("`language` = #{language,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            sql.SET("city = #{city,jdbcType=VARCHAR}");
        }
        
        if (record.getProvince() != null) {
            sql.SET("province = #{province,jdbcType=VARCHAR}");
        }
        
        if (record.getCountry() != null) {
            sql.SET("country = #{country,jdbcType=VARCHAR}");
        }
        
        if (record.getHeadimgurl() != null) {
            sql.SET("headimgurl = #{headimgurl,jdbcType=VARCHAR}");
        }
        
        if (record.getUnionid() != null) {
            sql.SET("unionid = #{unionid,jdbcType=VARCHAR}");
        }
        
        if (record.getWxAppid() != null) {
            sql.SET("wx_appid = #{wxAppid,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, YsWxUserExample example, boolean includeExamplePhrase) {
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